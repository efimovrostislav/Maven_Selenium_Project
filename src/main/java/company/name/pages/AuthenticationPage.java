package company.name.pages;

import company.name.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static io.qameta.allure.Allure.step;


/**
 * класс для авторизации
 */
public class AuthenticationPage extends AbstractPage {

    @FindBy(id = "header-lk-button")                    //кнопка открытия окна авторизации
    private WebElement loginButton;

    @FindBy(id = "login")
    private WebElement loginFiled;                      //поле для ввода логина при входе

    @FindBy(css = "[type=password]")
    private WebElement passwordField;                   //поле для ввода пароля

    @FindBy(css = "[ng-tr=\"WHE1.WHE4\"]")              // Кнопка войти в аккаунт
    private WebElement clickLogIn;

    @FindBy(id = "logout")                              // Кнопка выйти
    private WebElement clickLogOut;

    @FindBy(className = "error-block")                  // Поле ошибки при неверном указании email или пароля
    private WebElement InCorrectEmailOrPasswordError;

    @FindBy(css = ".validation-invalid")                // Поле ошибки при пустом email или пароле
    private WebElement InCorrectEmailOrPasswordNull;


    /**
     * Конструктор. Загружает ссылку на тест-стенд из файла конфигурации и подгружает указанные веб-элементы
     */
    public AuthenticationPage() {
        super(DriverManager.getDriver());
    }

    @Step("Открыть тестовый стенд")
    public void openTestStand(){
        DriverManager.getDriver().get(getStand());
        checkAndScreenShot("Проверяем активность кнопки 'Войти'", checkLoginButton(), "Open test stand exception");
    }

    @Step("Открываем PopUp")
    public void openPopUp() {
        loginButton.click();
        checkAndScreenShot("Проверяем открытие окна авторизации", checkClickLogIn(), "Open popUp exception");
    }

    @Step("Ввести e-mail")
    public void inputEmail (String email){
        loginFiled.sendKeys(email);
        checkAndScreenShot("Проверяем правильность ввода email",checkLoginFiled(email),"Input email authorization exception");
    }

    @Step("Ввести пароль")
    public void inputPassword(String password){
        passwordField.sendKeys(password);
        checkAndScreenShot("Проверяем правильность ввода пароля", checkPasswordField(password), "Input password authorization exception");
    }

    @Step("Нажать кнопку войти")
    public void clickAuthentication(){
        clickLogIn.click();
        checkAndScreenShot("Проверяем активность кнопки 'Выйти'", checkClickLogOut(), "Client login exception");
    }



    @Step("Нажать кнопку выйти")
    public void logOut(){
        clickLogOut.click();
        checkAndScreenShot("Проверяем активность кнопки 'Войти'", checkLoginButton(), "Client LogOut exception");
    }

    /**
     * проверка кнопки открытия PopUp
     * @return возвращает true если найден объект
     */
    public boolean checkLoginButton() {
        return loginButton.isEnabled();
    }

    /**
     * проверка поля email
     * @param email корректный email который ввели на предыдущем шаге
     * @return возвращает true если совпадает email
     */
    public boolean checkLoginFiled(String email) {
        return loginFiled.getAttribute("value").equals(email);
    }

    /**
     * проверка поля пароля
     * @param password корректный пароль который ввели на предыдущем шаге
     * @return возвращает true если совпадает пароль
     */
    public boolean checkPasswordField(String password) {
        return passwordField.getAttribute("value").equals(password);
    }

    /**
     * проверка кнопки входа
     * @return возвращает true если найден объект
     */
    public boolean checkClickLogIn() {
        return clickLogIn.isEnabled();
    }

    /**
     * проверка кнопки выхода из личного кабинета
     * @return возвращает true если найден объект
     */
    public boolean checkClickLogOut() {
        return clickLogOut.isEnabled();
    }

}
