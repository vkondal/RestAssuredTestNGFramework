package com.spotify.oauth2.utils;

import java.util.Properties;

public class DataLoader {
    private static DataLoader dataLoader;
    private final Properties properties;

    private DataLoader() {
        properties = PropertyUtils.propertiesLoader("src/test/resources/data.properties");
    }

    public static DataLoader createInstance() {
        if (dataLoader == null) {
            dataLoader = new DataLoader();
        }
        return dataLoader;
    }

    //Now we need to read the properties from data.properties file 1 by 1
    public String getUserID() {
        String prop = properties.getProperty("user_id");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property user_id is not set in the config.properties file");
        }
    }

    public String getPlayListID() {
        String prop = properties.getProperty("playlist_id");
        if (prop != null) {
            return prop;
        } else {
            throw new RuntimeException("property playlist_id is not set in the config.properties file");
        }
    }
}

