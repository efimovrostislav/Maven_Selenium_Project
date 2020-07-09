package company.name.pages;

import com.sun.source.tree.AssertTree;
import company.name.DriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Класс для стартовой страницы
 * @author Rostislav Efimov
 */
public class FirstPage extends AbstractPage {
    /**
     * Локаторы поиска на страницах
     */
    @FindBy(id = "header-lk-button")                    //кнопка открытия окна авторизации
    private WebElement loginButton;

    @FindBy(css = ".nl-header-link[ng-tr=\"NLHEA.NLHEA1\"]")
    private WebElement abouteProductButton;                           // кнопка перейти на страницу "О продукте"

    @FindBy(css = ".nl-header-link[ng-tr=\"NLHEA.NLHEA2\"]")
    private WebElement pricesButton;                                     // кнопка перейти на страницу "Цены"

    @FindBy(css = ".nl-header-link[ng-tr=\"NLHEA.NLHEA3\"]")
    private WebElement faqButton;                                       // кнопка перейти на страницу "FAQ"

    @FindBy(css = ".nl-header-link[ng-tr=\"NLHEA.NLHEA4\"]")
    private WebElement wantTestSiteButton;                             // кнопка перейти на страницу "Хочу тестировать сайты"

    @FindBy(css = "[ng-tr=\"NLTES.NLTES47\"]")          //Кнопка "Начать тестирование" на странице "Хочу тестировать сайты"
    private WebElement startTest;

    @FindBy(css = "[ng-tr=\"NLFAQ.NLFAQ1\"]")           //Кнопка "Когда проводить юзабилити-тестирование?" на странице "FAQ"
    private WebElement whenDoTest;

    @FindBy(css = "[ng-tr=\"NLPRC.NLPRC1\"]")           // проверка наличия элемента "Тарифы на использование UXCrowd" на странице "Цены"
    private WebElement checkPrice;

    @FindBy(css = "[ng-tr=\"NLABO.NLABO2\"]")          //Кнопка "Попробовать бесплатно" на странице "О продукте"
    private WebElement startFree;

    /**
     * Конструктор. Загружает ссылку на тест-стенд из файла конфигурации и подгружает указанные веб-элементы
     */
    public FirstPage() { super(DriverManager.getDriver());}
    //int timeWait = 10;


    @Step("Открыть тестовый стенд")
    public void openTestStand(){
        DriverManager.getDriver().get(getStand());
        checkAndScreenShot("Проверяем активность кнопки 'Войти'", checkLoginButton(), "Open test stand exception");
    }

    @Step("Открыть страницу 'Хочу тестировать сайты'")
    public void openWantTestSitePage() {
//        new WebDriverWait(DriverManager.getDriver(), timeWait).until((d) -> faqButton.isDisplayed());
        wantTestSiteButton.click();
        checkAndScreenShot("Проверяем активность кнопки 'Начать тестирование'", checkStartTestButton(), "Open want test site exception");
    }

    @Step("Открыть страницу 'FAQ'")
    public void openFAQPage(){
//        new WebDriverWait(DriverManager.getDriver(), timeWait).until((d) -> pricesButton.isDisplayed());
        faqButton.click();
        checkAndScreenShot("Проверяем наличие кнопки 'Когда проводить юзабилити-тестирование?'", checkWhenDoTest(), "Open FAQ page exception");
    }

    @Step("Открыть страницу 'Цены'")
    public void openPricesPage(){
//        new WebDriverWait(DriverManager.getDriver(), timeWait).until((d) -> abouteProductButton.isDisplayed());
        pricesButton.click();
        checkAndScreenShot("Проверяем наличие элемента 'Тарифы на использование UXCrowd'", tariffsCheckPrices(), "Open prices page exception");
    }

    @Step("Открыть страницу 'О продукте'")
    public void openAbouteProductPage(){
//        new WebDriverWait(DriverManager.getDriver(), timeWait).until((d) -> wantTestSiteButton.isDisplayed());
        abouteProductButton.click();
        checkAndScreenShot("Проверяем активность кнопки 'Попробовать бесплатно'", checkStartFree(), "Open about product page exception");
    }



    /**
     * проверка кнопки открытия PopUp
     * @return возвращает true если найден объект
     */
    public boolean checkLoginButton() {
        return loginButton.isEnabled();
    }

    /**
     *  проверка активности кнопки 'Начать тестирование'
     *  @return возвращает true если найден объект
     */
    public boolean checkStartTestButton(){
        return startTest.isEnabled();
    }

    /**
     *  проверка наличия кнопки 'Когда проводить юзабилити-тестирование?'
     * @return возвращает true если найден объект
     */
    public boolean checkWhenDoTest(){
        return whenDoTest.isEnabled();
    }

    /**
     * проверка наличия элемента 'Тарифы на использование UXCrowd'
     * @return возвращает true если найден объект
     */
    public boolean tariffsCheckPrices(){
        return checkPrice.isEnabled();
    }

    /**
     * проверка активности кнопки 'Попробовать бесплатно'
     * @return возвращает true если найден объект
     */
    public boolean checkStartFree(){
        return startFree.isEnabled();
    }
}
