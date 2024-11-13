package automation.example.com.support;

import automation.example.com.utils.Reports;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;


import static java.lang.System.out;

public class Hooks extends BaseTest {

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
        testCount++;
        Reports.attachScreenshot();
        tearDown();
    }

    @AfterAll
    public static void endSuite() {
        out.println("Cenários executados: " + testCount);
    }
}
