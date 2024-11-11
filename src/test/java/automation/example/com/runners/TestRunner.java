package automation.example.com.runners;

import automation.example.com.tests.LoginMobileTest;
import automation.example.com.tests.LoginWebTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags("regression_web")
@DisplayName("Classe de execução dos testes - Swag Labs Application")
@SelectPackages("src.test.java.automation.example.com.tests")
@SelectClasses({
    LoginWebTest.class,
    LoginMobileTest.class
})
public class TestRunner {}