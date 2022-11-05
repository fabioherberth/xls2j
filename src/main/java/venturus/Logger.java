package venturus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String COMPLETE_MESSAGE = "[%s] %s - %s";

    public static void debug(String message) {
        System.out.println(build("DEBUG", message));
    }

    public static void error(String message) {
        System.err.println(build("ERROR", message));
    }

    private static String build(String type, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String datetime = LocalDateTime.now().format(formatter);
        return String.format(COMPLETE_MESSAGE, type, datetime, message);
    }
}
