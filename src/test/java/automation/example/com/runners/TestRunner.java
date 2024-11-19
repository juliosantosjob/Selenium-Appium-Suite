package automation.example.com.runners;

import automation.example.com.usecases.web.LoginWebTest;
import automation.example.com.usecases.mobile.LoginMobileTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeTags("login_web")
@DisplayName("Classe de execução dos testes - Swag Labs Application")
@SelectPackages("src.test.java.automation.example.com.modules")
@SelectClasses({
    LoginWebTest.class,
    LoginMobileTest.class
})

public class TestRunner {}