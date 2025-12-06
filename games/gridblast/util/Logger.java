package games.gridblast.util;

public final class Logger {

    private static boolean enabled = true;

    private Logger() {}

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    public static void log(String message) {
        if (enabled) {
            System.out.println("[GridBlast] " + message);
        }
    }
}
