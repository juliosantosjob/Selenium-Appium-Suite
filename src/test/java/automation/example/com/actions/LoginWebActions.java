package automation.example.com.actions;

import static automation.example.com.pages.LoginMobilePages.*;
import static automation.example.com.pages.LoginWebPages.*;
import static automation.example.com.pages.LoginWebPages.loginButton;
import static automation.example.com.support.BaseTest.*;

public class LoginWebActions {

    public void openApp() {
        visit(baseUrl);
        visibleText(titleLoginPage);
    }

    public void fillForm(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
    }

    public void submitLogin() {
        click(loginButton);
    }

    public String getTextProduct() {
        return grabText(fieldProduct);
    }
}