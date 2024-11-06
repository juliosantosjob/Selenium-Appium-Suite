package automation.example.com.actions;

import static automation.example.com.pages.LoginMobilePages.*;
import static automation.example.com.support.BaseTest.*;

public class LoginMobileActions {

    public void openApp() {
        visible(appLogo);
    }

    public void fillForm(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
    }

    public void submitLogin() {
        click(loginButton);
    }

    public String getTextProduct() {
        return grabText(textProduct);
    }
}