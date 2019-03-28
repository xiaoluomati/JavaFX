package util;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {

    private static final String CONFIG_PATH = "game.properties";

    private static Properties properties = readProp();

    public static String getProp(String key){
        return properties.getProperty(key);
    }

    private static Properties readProp(){
        Properties p = new Properties();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(CONFIG_PATH), "utf-8"))){
            p.load(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }

}
