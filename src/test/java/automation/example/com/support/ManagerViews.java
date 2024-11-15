package automation.example.com.support;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import static java.lang.Integer.parseInt;

public class ManagerViews extends BaseTest {
    private static final String viewPort = System.getProperty("SET_VIEWPORT");
    private static String[] size;

    public static void setNewViewport(WebDriver driver) {
        if (viewPort == null || viewPort.isEmpty()) {
            driver.manage().window().maximize();
        } else {
            size = viewPort.toLowerCase().split("x");
            if (size.length != 2) {
                throw new RuntimeException("Formato inválido de viewport. Exemplos: -DSET_VIEWPORT=1920x1080");
            }
            int width = parseInt(size[0]);
            int height = parseInt(size[1]);
            driver.manage().window().setSize(new Dimension(width, height));
        }
    }
}