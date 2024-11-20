package automation.example.com.actions.mobile;

import static automation.example.com.elements.mobile.LoginMobileElements.*;
import static automation.example.com.support.BaseTest.*;
import static java.lang.System.out;

public class LoginMobileActions {

    public void openApp() {
        getElement(appLogo).isDisplayed();
        out.println("- Abre o aplicativo e verifica a visibilidade do logo.");
    }

    public void fillForm(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        out.println("- Preenche as credenciais do usuário");
    }

    public void submitLogin() {
        click(loginButton);
        out.println("- Submete o formulário de login");
    }

    public String getTextProduct() {
        String productText = grabText(textProduct);
        out.println("- Obtém o texto: " + productText);
        return productText;
    }
}