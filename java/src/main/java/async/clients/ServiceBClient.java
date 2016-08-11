package async.clients;

import io.atlassian.fugue.Either;
import org.springframework.stereotype.Service;
import async.services.WebServiceB;

import java.io.IOException;

@Service
public class ServiceBClient implements ServiceClient {
    @Override
    public Either<Exception, String> getData() {
        final WebServiceB supplierWS = new WebServiceB();
        try {
            return Either.right(supplierWS.process());
        } catch (IOException e) {
            return Either.left(e);
        }
    }
}
