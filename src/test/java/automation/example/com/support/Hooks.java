package automation.example.com.support;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static java.lang.System.out;


public class Hooks extends BaseTest {

    @BeforeEach
    public void beforeTests(TestInfo testInfo) {
        out.println("*********************************************************");
        out.println("Testes em Execução...");
        out.println("Nome do Cenário: [" + testInfo.getDisplayName() + "]");
        out.println("Tag de Execução: " + testInfo.getTags());
        out.println("*********************************************************");
    }

    @AfterEach
    public void afterTests() {
        out.println("*********************************************************");
        out.println("Testes Concluídos...");
        out.println("*********************************************************");
        tearDown();
    }
}