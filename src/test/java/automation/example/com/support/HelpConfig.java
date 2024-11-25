package automation.example.com.support;

import org.openqa.selenium.Dimension;

import java.time.Duration;

import static java.lang.Integer.parseInt;

public class HelpConfig extends BaseTest {
    private static final String viewPort = System.getProperty("SET_VIEWPORT");
    private static String[] size;

    public static void helpConfig(String driverType) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        if (driverType.equalsIgnoreCase("web")) {
            if (viewPort == null || viewPort.isEmpty()) {
                getDriver().manage().window().maximize();
            } else {
                size = viewPort.toLowerCase().split("x");
                if (size.length != 2) {
                    throw new IllegalArgumentException("Invalid viewport size: " + viewPort);
                }
                getDriver().manage().window().setSize(new Dimension(parseInt(size[0]), parseInt(size[1])));
            }
        }
    }
}