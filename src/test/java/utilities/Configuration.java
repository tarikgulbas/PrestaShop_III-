package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public static Properties pro;

    static {
        String path = "configuration.properties";

        try {

            FileInputStream file = new FileInputStream(path);
            pro = new Properties();
            pro.load(file);
            file.close();

        } catch (FileNotFoundException e) {
            System.out.println("Path :" + path + " - NOT FOUND");
        } catch (IOException e) {
            System.out.println("FILE ERROR");
        }
    }



    public static String getProperty(String key){
        return pro.getProperty(key);
    }

}
