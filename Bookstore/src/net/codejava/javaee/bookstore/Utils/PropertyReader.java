package net.codejava.javaee.bookstore.Utils;

import net.codejava.javaee.bookstore.servlets.ControllerServlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {


    public static String getPropertyStr(String StrProperty) {
        Properties property = new Properties();
        ClassLoader classLoader = ControllerServlet.class.getClassLoader();
        InputStream input = classLoader.getResourceAsStream("confDb.properties");

        try {
            property.load(input);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return property.getProperty(StrProperty);
    }
}
