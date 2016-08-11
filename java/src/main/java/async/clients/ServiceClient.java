package async.clients;

import io.atlassian.fugue.Either;

public interface ServiceClient {
    Either<Exception, String> getData();
}
