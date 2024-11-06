package automation.example.com.support;

import java.io.FileInputStream;
import java.util.Properties;

public class EnvProperties {
    private static final String ENV_PROP = "/src/test/java/automation/example/com/test-config/enviroments.properties";
    private static final String COMPLETE_PATH = System.getProperty("user.dir") + ENV_PROP;
    private static final Properties properties = new Properties();;

    private static Properties loadProperties() {
        try {
            properties.load(new FileInputStream(COMPLETE_PATH));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar properties no caminho: " + COMPLETE_PATH, e);
        }
        return properties;
    }

    public static String getEnv(String key) {
        return loadProperties().getProperty(key);
    }
}