package automation.example.com.support;

import org.openqa.selenium.Dimension;

import java.time.Duration;

import static java.lang.Integer.parseInt;

public class HelpConfig extends BaseTest {
    private static final String viewPort = System.getProperty("SET_VIEWPORT");
    private static String[] size;

    public static void helpConfig(String driverType) {
        if (driverType.equalsIgnoreCase("web")) {
            if (viewPort == null || viewPort.isEmpty()) {
                getDriver().manage().window().maximize();
            } else {
                size = viewPort.toLowerCase().split("x");
                if (size.length != 2) {
                    System.err.println("Invalid viewport format expected: -DSET_VIEWPORT=1920x1080");
                }
                int width = parseInt(size[0]);
                int height = parseInt(size[1]);

                getDriver().manage().window().setSize(new Dimension(width, height));
            }
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
    }
}