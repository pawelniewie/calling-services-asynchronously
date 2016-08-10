package aggregate;

import clients.ServiceClient;
import io.atlassian.fugue.Either;
import io.atlassian.fugue.Eithers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Iterables.concat;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ConnectionsService implements DisposableBean, InitializingBean {
    private static final int MAX_WAIT_TIME = 5;

    @Autowired
    private List<ServiceClient> serviceClients;

    private ThreadPoolExecutor executor;

    public ResultsDto getConnections() throws InterruptedException {
        List<Future<Either<Exception, String>>> results = executor.invokeAll(serviceClients
                .stream()
                .map(serviceClient -> {
                            Callable<Either<Exception, String>> callable = () ->
                                    serviceClient.getData();
                            return callable;
                        }
                ).collect(toList()), MAX_WAIT_TIME, TimeUnit.SECONDS);

        // get only all the successful results
        Iterable<String> connectionsForEachProvider = Eithers.filterRight(results
                .stream()
                .filter(Future::isDone)
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        return Either.<Exception, String>left(e);
                    }
                })
                .collect(toList()));

        return ResultsDto.builder()
                .results(copyOf(concat(connectionsForEachProvider)))
                .build();
    }

    @Override
    public void destroy() throws Exception {
        executor.shutdownNow();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        executor = new ThreadPoolExecutor(serviceClients.size(), Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<>());
        // it will immediately create threads
        executor.prestartAllCoreThreads();
    }
}
