package automation.example.com.actions;

import static automation.example.com.pages.LoginWebPages.*;
import static automation.example.com.pages.LoginWebPages.loginButton;
import static automation.example.com.support.BaseTest.*;
import static java.lang.System.out;

public class LoginWebActions {

    public void openApp() {
        visit(baseUrl);
        visibleText(titleLoginPage);
        out.println("** Abre o aplicativo e verifica o texto: " + titleLoginPage + " **");
    }

    public void fillForm(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        out.println("** Preenche as credenciais do usuário **");
    }

    public void submitLogin() {
        click(loginButton);
        out.println("** Submete o formulário de login **");
    }

    public String getTextProduct() {
        String productText = grabText(fieldProduct);
        out.println("** Obtém o texto do produto: " + productText + " **");
        return productText;
    }
}