package automation.example.com.support;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import org.monte.screenrecorder.ScreenRecorder;

import static automation.example.com.utils.Reports.*;
import static automation.example.com.utils.Recordings.startRecord;
import static automation.example.com.utils.Recordings.stopRecord;
import static java.lang.System.out;

public class Hooks extends BaseTest {
    ScreenRecorder screenRecorder;

    @BeforeEach
    public void init(TestInfo testInfo) throws Exception {
        startRecord(testInfo.getDisplayName());
        out.println("───────────────────────────────────────────────────────────────");
        out.println("Feature: " + getClass().getAnnotation(DisplayName.class).value());
        out.println("Scenario: " + testInfo.getDisplayName());
        out.println("Tags: " + testInfo.getTags());
        out.println(" ");
    }

    @AfterEach
    public void end() throws Exception {
        out.println("───────────────────────────────────────────────────────────────\n\n");
        attachScreenshot();
        stopRecord();
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
