package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static volatile Properties props;
    private static final Object lock = new Object();

    private static Properties getProperties() {
        if (props == null) {
            synchronized (lock) {
                if (props == null) {
                    props = new Properties();
                    // Load properties from src/test/resources/config.properties via classpath
                    try (InputStream in = Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream("config.properties")) {
                        if (in != null) {
                            props.load(in);
                        } else {
                            System.err.println("Warning: config.properties not found on classpath");
                        }
                    } catch (IOException e) {
                        throw new ExceptionInInitializerError(
                                "Failed to load config.properties: " + e.getMessage());
                    }
                }
            }
        }
        return props;
    }

    public static String get(String key) {
        return getProperties().getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return getProperties().getProperty(key, defaultValue);
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

    public static Properties getAll() {
        return props;
    }

}