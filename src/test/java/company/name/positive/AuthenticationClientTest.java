package company.name.positive;


import company.name.pages.AuthenticationPage;
import org.testng.annotations.Test;

/**
 * Тест проверяет возможность войти с заданными логином и паролем
 * @see AuthenticationPage
 */
public class AuthenticationClientTest {

    private String email = "kicoti9729@kartk5.com";  // Почта заранее зарагестрированного пользователя
    private String password = "QlwS1Z";  // Пароль от личного кабинета клиента

    private AuthenticationPage step = new AuthenticationPage();

    @Test
    public void authenticationClient() {
        step.openTestStand();
        step.openPopUp();
        step.inputEmail(email);
        step.inputPassword(password);
        step.clickAuthentication();
        step.logOut();
    }

}
