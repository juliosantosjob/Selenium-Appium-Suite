package automation.example.com.support;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;

import static automation.example.com.utils.Reports.attachScreenshot;
import static java.lang.System.out;

public class Hooks extends BaseTest {

    @BeforeEach
    public void init(TestInfo testInfo) {
        out.println("───────────────────────────────────────────────────────────────");
        out.println("Feature: " + getClass().getAnnotation(DisplayName.class).value());
        out.println("Scenario: " + testInfo.getDisplayName());
        out.println("Tags: " + testInfo.getTags());
        out.println(" ");
    }

    @AfterEach
    public void end() {
        out.println("───────────────────────────────────────────────────────────────\n\n");
        attachScreenshot();
        testCount++;
        tearDown();
    }

    @BeforeAll
    public static void initSuite() {
        out.println("Starting test...\n");
    }

    @AfterAll
    public static void endSuite() {
        out.println("Count of scenarios executed: " + testCount);
        out.println("Ending test...\n");
    }
}
