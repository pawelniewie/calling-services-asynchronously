package async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String []args) throws Exception {
        //We've setup Maven & Gradle for your convenience. Please feel free to pick one (you can remove other config files for clarity)
        //
        //The CLI should work using one of the following commands:
        //
        // mvn clean install exec:java
        // gradle clean run
        //

        SpringApplication.run(Main.class, args);
    }

}

