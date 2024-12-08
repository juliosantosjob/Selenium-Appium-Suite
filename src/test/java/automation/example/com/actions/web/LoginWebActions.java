package automation.example.com.actions.web;

import automation.example.com.support.EnvProperties;

import static automation.example.com.elements.web.LoginWebElements.*;
import static automation.example.com.support.BaseTest.*;
import static java.lang.System.out;

public class LoginWebActions {
    public static final String baseUrl = EnvProperties.getEnv("app.base.url");

    public void openSite() {
        visit(baseUrl);
        visible(loginButton);
        out.println("- Abre o site");
    }

    public void fillForm(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        out.println("- Preenche as credênciais de login");
    }

    public void submitLogin() {
        click(loginButton);
        out.println("- Submete o formulário de login");
    }

    public String getTextProduct() {
        String productText = grabText(fieldProduct);
        out.println("- Obtém o texto do produto: " + productText);
        return productText;
    }

    public String getMessageErrorLogin() {
        String errorMessage = grabText(messageError);
        out.println("- Obtém o texto do erro: " + errorMessage);
        return errorMessage;
    }

    public void doLogin(String username, String password) {
        this.openSite();
        this.fillForm(username, password);
        this.submitLogin();
        out.println("- Efetua login");
    }
}