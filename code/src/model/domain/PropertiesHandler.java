package model.domain;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.PropertyPermission;

public class PropertiesHandler {
    String file = "code" + File.separator + "src" + File.separator + "bestanden" + File.separator + "settings.properties";
    public void write(Properties properties){

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            properties.store(os, "KassaApp properties");
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Properties read(){
        Properties properties = new Properties();
        try {
            InputStream fi = new FileInputStream(file);
            properties.load(fi);
            fi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}