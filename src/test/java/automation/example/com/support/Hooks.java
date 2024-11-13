package automation.example.com.support;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;

import static automation.example.com.utils.Reports.attachScreenshot;
import static java.lang.System.out;

public class Hooks extends BaseTest {
    private static int testCount = 0;

    @BeforeEach
    public void init(TestInfo testInfo) {
        out.println("************************************************************");
        out.println("Funcionalidade: " + getClass().getAnnotation(DisplayName.class).value());
        out.println("Nome do Cenário: " + testInfo.getDisplayName());
        out.println("Tag de Execução: " + testInfo.getTags());
        out.println("************************************************************");
    }

    @AfterEach
    public void end() {
        out.println("\n");
        attachScreenshot();
        tearDown();
        testCount++;
    }

    public static void endSuite() {
        out.println("Total de cenários executados: " + testCount);
    }
}
