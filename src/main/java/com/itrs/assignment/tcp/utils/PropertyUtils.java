package com.itrs.assignment.tcp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyUtils {

    public static final String SERVER_IP_ADDRESS = "127.0.0.1";
    public static final int SERVER_PORT_NUMBER = 443;
    public static final int CLIENT_PORT_NUMBER = 443;
    public static final String APP_PROPS = "application.properties";
    private static final  Properties properties = new Properties();

    /**
     * @author - Syed Imran
     * loadProperties is for future enhancements to read properties from config.
     * @return
     */
    public static Properties loadProperties() {
        try (InputStream configFile =  PropertyUtils.class.getResourceAsStream(APP_PROPS)) {
            properties.load(configFile);
        } catch (IOException e) {
            System.err.println("Error loading properties from " + APP_PROPS + ": " + e.getMessage());
        }
        return properties;
    }
}