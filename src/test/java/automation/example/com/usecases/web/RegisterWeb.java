package automation.example.com.usecases.web;

import automation.example.com.support.BaseTest;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.time.Duration;

import static java.lang.Thread.sleep;

public class RegisterWeb extends BaseTest {

    @BeforeEach
    public void setup() throws MalformedURLException {
        changeDriverTo("web");
    }

    // Base URL: https://advantageonlineshopping.com/#/

    @Test
    @Order(1)
    @Tag("test_sample")
    @DisplayName("Teste de cadastro web")
    public void testRegister() throws InterruptedException {

        getDriver().get("https://advantageonlineshopping.com/#/");
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        getElement(By.xpath("//*[@id='menuUserSVGPath']")).click();
        waitBeClickable(By.xpath("//*[@translate='CREATE_NEW_ACCOUNT']"));

        getElement(By.xpath("//*[@name='usernameRegisterPage']")).sendKeys("Roberto");
        getElement(By.xpath("//*[@name='emailRegisterPage']")).sendKeys("emailTeste@gmail.com");
        getElement(By.xpath("//*[@name='passwordRegisterPage']")).sendKeys("Xp9@Tq7&LgZ2");
        getElement(By.xpath("//*[@name='confirm_passwordRegisterPage']")).sendKeys("Xp9@Tq7&LgZ2");
        getElement(By.xpath("//*[@name='i_agree']")).click();


        sleep(1000);
        getDriver().quit();
    }
}