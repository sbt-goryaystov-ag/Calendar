package economic.auto.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    private final Properties properties = new Properties();
    private static TestProperties INSTANCE = null;
    private TestProperties(){
        try {
            properties.load(new FileInputStream(new File("src/test/resources/" + System.getProperty("stand", "test") + ".properties")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TestProperties getInstance() {
        if (INSTANCE == null){
            INSTANCE = new TestProperties();
        }
        return INSTANCE;
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}