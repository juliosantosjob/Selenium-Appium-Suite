package automation.example.com.actions;

import static automation.example.com.pages.LoginWebPages.*;
import static automation.example.com.pages.LoginWebPages.loginButton;
import static automation.example.com.support.BaseTest.*;

public class LoginWebActions {

    public void openApp() {
        visit(baseUrl);
        visibleText(titleLoginPage);
        System.out.println("Abre o aplicativo e verifica a visibilidade do título da página de login.");
    }

    public void fillForm(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        System.out.println("Preenche o formulário com o usuário: " + username + " e senha.");
    }

    public void submitLogin() {
        click(loginButton);
        System.out.println("Submete o login.");
    }

    public String getTextProduct() {
        String productText = grabText(fieldProduct);
        System.out.println("Obtém o texto do produto: " + productText);
        return productText;
    }
}