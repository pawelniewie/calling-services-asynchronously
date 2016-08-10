package services;

import java.io.IOException;
import java.util.Random;

/**
 * A fake web service, that represents a data supplier.
 */
public class WebServiceA {
    public String list() throws IOException {
        if (Boolean.valueOf(System.getProperty("com.pawelniewiadomski.serviceAIsDown", "false"))) {
            throw new java.net.ConnectException("Connection failed!");
        }

        final Random random = new Random();

        try {
            Thread.sleep(random.nextInt(10)*1000);
        } catch (InterruptedException e) {}

        return "";
    }
}
