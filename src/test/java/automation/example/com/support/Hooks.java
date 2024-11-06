package automation.example.com.support;

import org.junit.jupiter.api.AfterEach;

public class Hooks extends BaseTest {

    @AfterEach
    public void afterTest() {
        tearDown();
    }
}