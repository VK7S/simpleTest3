import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GetSettings {
    public static String USER = "";
    public static String PASS = "";
    public static String CHROME_PATH = "";
    public static String URL = "";
    public static String API = "";
    public static String APITOKEN = "";
    public static String APIKEY = "";

    public static void get() {
        FileInputStream fis;
        Properties property = new Properties();
        String path = System.getProperty("user.dir") + File.separator
                + "src" + File.separator + "main" + File.separator + "resources"
                + File.separator + "config.properties";

        try {
            fis = new FileInputStream(path);
            property.load(fis);
            fis.close();

            USER = property.getProperty("name");
            PASS = property.getProperty("password");
            CHROME_PATH = property.getProperty("chromepath");
            URL = property.getProperty("url");
            API = property.getProperty("api");
            APITOKEN = property.getProperty("apitoken");
            APIKEY = property.getProperty("apikey");

            System.out.println(
                    String.format("Current settings: USER: %s, PASSWORD: %s",
                            USER, PASS));

        } catch (Exception e) {
            System.err.println("ERROR: config.properties is not found or incorrect");
        }

    }
}
