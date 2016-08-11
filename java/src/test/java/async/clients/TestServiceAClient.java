package async.clients;

import io.atlassian.fugue.Either;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.ConnectException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestServiceAClient {

    @Before
    public void setUp() {
        System.setProperty("com.pawelniewiadomski.serviceAIsDown", "true");
    }

    @After
    public void tearDown() {
        System.getProperties().remove("com.pawelniewiadomski.serviceAIsDown");
    }

    @Test
    public void shouldReturnException() {
        ServiceAClient factory = new ServiceAClient();
        Either<Exception, String> result = factory.getData();
        assertTrue("didn't return exception", result.isLeft());
        assertThat(result.left().get(), IsInstanceOf.instanceOf(ConnectException.class));
    }

}
