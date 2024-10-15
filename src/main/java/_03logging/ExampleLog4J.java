package _03logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleLog4J {

    private static final Logger logger = LogManager.getLogger(ExampleLog4J.class);

    public static void main(String[] args) {
        String userId = "42";
        Wrapper param = new Wrapper(userId);
        System.out.println("Log 1:");
        logger.info("Benutzer mit der ID " + param + " hat sich angemeldet");
        System.out.println("Log 2:");
        logger.info("Benutzer mit der ID {} hat sich angemeldet", param);
        System.out.println("Log 3:");
        logger.info(() -> "Benutzer mit der ID " + param + " hat sich angemeldet");
    }

}
