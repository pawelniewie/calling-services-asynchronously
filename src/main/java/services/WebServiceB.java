package services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.joda.time.Duration;
import org.joda.time.LocalDateTime;

/**
 * A fake web service, that represents a flight supplier. Keep in mind that YOU CAN'T MODIFY this class
 * in this assignment.
 */
public class WebServiceB {

    public String getConnections() throws IOException {
        if (Boolean.valueOf(System.getProperty("com.pawelniewiadomski.serviceBIsDown", "false"))) {
            throw new java.net.ConnectException("Connection failed!");
        }

        final Random random = new Random();

        try {
            Thread.sleep(random.nextInt(10) * 1000);
        } catch (InterruptedException e) {
        }

        return "";
    }

}
