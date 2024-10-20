package org.yandrut.utils;

import java.util.ResourceBundle;

public class DataReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("env", "test"));

    private DataReader(){}

    public static String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}