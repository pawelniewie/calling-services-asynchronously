package async.clients;

import async.services.WebServiceA;
import io.atlassian.fugue.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ServiceAClient implements ServiceClient {

    @Override
    public Either<Exception, String> getData() {
        final WebServiceA supplierWS = new WebServiceA();
        try {
            return Either.right(supplierWS.list());
        } catch (IOException e) {
            return Either.left(e);
        }
    }
}
