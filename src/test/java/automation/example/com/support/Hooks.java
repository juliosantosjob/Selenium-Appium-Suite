package automation.example.com.support;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.util.logging.Level;

public class Hooks extends BaseTest {

    @BeforeEach
    public void beforeTests(TestInfo testInfo) {
        System.out.println("********************************************");
        System.out.println("Nome do cenario: " + testInfo.getDisplayName());
        System.out.println("Tag de execução: " + testInfo.getTags());
        System.out.println("********************************************");
        System.out.println("\n");
    }

    @AfterEach
    public void afterTests() {
        tearDown();
    }
}