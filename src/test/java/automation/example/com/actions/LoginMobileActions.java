package automation.example.com.actions;

import static automation.example.com.pages.LoginMobilePages.*;
import static automation.example.com.support.BaseTest.*;

public class LoginMobileActions {

    public void openApp() {
        visible(appLogo);
        System.out.println("Abre o aplicativo e verifica a visibilidade do logo.");
    }

    public void fillForm(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        System.out.println("Preenche o formulário com o usuário: " + username + " e senha.");
    }

    public void submitLogin() {
        click(loginButton);
        System.out.println("Submete o login.");
    }

    public String getTextProduct() {
        String productText = grabText(textProduct);
        System.out.println("Obtém o texto do produto: " + productText);
        return productText;
    }
}