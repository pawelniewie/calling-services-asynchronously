package async.clients;

import io.atlassian.fugue.Either;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.ConnectException;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestServiceBClient {
    @Before
    public void setUp() {
        System.setProperty("com.pawelniewiadomski.serviceBIsDown", "true");
    }

    @After
    public void tearDown() {
        System.getProperties().remove("com.pawelniewiadomski.serviceBIsDown");
    }

    @Test
    public void shouldReturnException() {
        ServiceBClient factory = new ServiceBClient();
        Either<Exception, String> result = factory.getData();
        assertTrue("didn't return exception", result.isLeft());
        assertThat(result.left().get(), IsInstanceOf.instanceOf(ConnectException.class));
    }
}
