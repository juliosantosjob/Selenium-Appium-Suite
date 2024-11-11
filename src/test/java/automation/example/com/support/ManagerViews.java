package automation.example.com.support;

import org.openqa.selenium.Dimension;

import static java.lang.Integer.parseInt;

public class ManagerViews extends BaseTest {
    public static String viewPort = System.getProperty("SET_VIEWPORT");

    public static void setNewViewport() {
        if (viewPort == null || viewPort.isEmpty()) {
            getDriver().manage().window().maximize();
        } else {
            String[] size = viewPort.split("X");

            if (size.length != 2) {
                throw new RuntimeException("Formato inválido de viewport. Exemplos: -DSET_VIEWPORT=1920x1080");
            }

            int width = parseInt(size[0]);
            int height = parseInt(size[1]);

            getDriver().manage().window().setSize(
                    new Dimension(width, height)
            );
        }
    }
}