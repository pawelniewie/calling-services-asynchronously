package async.services;

import java.io.IOException;
import java.util.Random;

/**
 * A fake web service, that represents a flight supplier. Keep in mind that YOU CAN'T MODIFY this class
 * in this assignment.
 */
public class WebServiceB {

    public String process() throws IOException {
        if (Boolean.valueOf(System.getProperty("com.pawelniewiadomski.serviceBIsDown", "false"))) {
            throw new java.net.ConnectException("Connection failed!");
        }

        final Random random = new Random();
        final int timeToSleep = random.nextInt(10) * 1000;
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
        }

        return Integer.toString(timeToSleep);
    }

}
