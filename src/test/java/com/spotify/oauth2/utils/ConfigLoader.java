package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {
    private static ConfigLoader configLoader;
    private final Properties properties;

    private ConfigLoader() {
        properties = PropertyUtils.propertiesLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader createInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    //Now we need to read the properties from config.properties file 1 by 1
    public String getClientID() {
        String prop = properties.getProperty("client_id");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property client_id is not set in the config.properties file");
        }
    }

    public String getClientSecret() {
        String prop = properties.getProperty("client_secret");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property client_secret is not set in the config.properties file");
        }
    }

    public String getGrantType() {
        String prop = properties.getProperty("grant_type");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property grant_type is not set in the config.properties file");
        }
    }

    public String getRefreshToken() {
        String prop = properties.getProperty("refresh_token");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property refresh_token is not set in the config.properties file");
        }
    }

}

