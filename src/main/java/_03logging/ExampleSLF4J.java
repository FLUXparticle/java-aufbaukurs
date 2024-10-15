package _03logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleSLF4J {

    private static final Logger logger = LoggerFactory.getLogger(ExampleSLF4J.class);

    public static void main(String[] args) {
        String userId = "42";
        Wrapper param = new Wrapper(userId);
        System.out.println("Log 1:");
        logger.info("Benutzer mit der ID " + param + " hat sich angemeldet");
        System.out.println("Log 2:");
        logger.info("Benutzer mit der ID {} hat sich angemeldet", param);
        // nur SLF4J v2
        System.out.println("Log 3:");
        logger.atInfo().log(() -> "Benutzer mit der ID " + param + " hat sich angemeldet");


        int newT = 15;
        int oldT = 16;

        // using traditional API
        logger.debug("Temperature set to {}. Old value was {}.", newT, oldT);

        // Verwendung der Fluent API, Log-Nachricht mit Argumenten
        logger.atDebug().log("Temperatur auf {} gesetzt. Alter Wert war {}.", newT, oldT);

        // Verwendung der Fluent API, Argumente einzeln hinzufügen und dann die Nachricht loggen
        logger.atDebug().setMessage("Temperatur auf {} gesetzt. Alter Wert war {}.")
                .addArgument(newT)
                .addArgument(oldT)
                .log();

        // Verwendung der Fluent API, ein Argument mit einem Supplier hinzufügen und dann die Nachricht loggen
        // Angenommen, die Methode t16() gibt den Wert 16 zurück.
        logger.atDebug().setMessage("Temperatur auf {} gesetzt. Alter Wert war {}.")
                .addArgument(() -> t16())
                .addArgument(oldT)
                .log();

    }

    private static int t16() {
        return 16;
    }

}
