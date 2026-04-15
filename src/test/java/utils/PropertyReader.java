package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final Properties props = new Properties();

    static {
        // Load properties from src/test/resources/config.properties via classpath
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            } else {
                System.err.println("Warning: config.properties not found on classpath");
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        String v = get(key);
        if (v == null) return defaultValue;
        try {
            return Integer.parseInt(v.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String v = get(key);
        if (v == null) return defaultValue;
        return Boolean.parseBoolean(v.trim());
    }

    /**
     * Expose the underlying Properties object for advanced usage.
     */
    public static Properties getAll() {
        return props;
    }

}