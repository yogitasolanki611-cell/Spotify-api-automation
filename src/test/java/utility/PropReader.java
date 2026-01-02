package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropReader {

    public static String readPropFile(String propToBeRead) throws IOException {

        Properties prop = new Properties();

        // 1️⃣ FIRST: Try environment variable (CI)
        String value = System.getenv(propToBeRead.toUpperCase());

        if (value != null) {
            System.out.println("Read from ENV: " + value);
            return value;
        }

        // 2️⃣ SECOND: Fallback to Config.properties (LOCAL)
        String projectPath = System.getProperty("user.dir");
        String configFilePath =
                projectPath + "/src/test/resources/Config.properties";

        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            prop.load(fis);
            value = prop.getProperty(propToBeRead);
            System.out.println("Read from FILE: " + value);
            return value;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(
                    "Config.properties not found AND environment variable not set for: "
                            + propToBeRead
                            + ". In CI, values must come from GitHub Secrets.",
                    e
            );
        }
    }

    // Optional local test
    public static void main(String[] args) throws IOException {
        readPropFile("refresh_token");
    }
}

