package company.name.pages;

import company.name.DriverManager;
import org.openqa.selenium.OutputType;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static io.qameta.allure.Allure.step;

/**
 * Асбтрактный класс страницы. Загружает ссылку на тест-стенд из файла конфигурации и подгружает указанные веб-элементы
 */
public abstract class AbstractPage {

    /**
     * Поле страницы тест-стенда, загруженного с файла конфигурации
     */
    private static String testStand;

    /**
     * Конструктор. Загружает ссылку на тест-стенд из файла конфигурации и подгружает указанные веб-элементы
     * @param driver вебдрайвер с которым мы работаем
     */
    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

        InputStream inputStream = DriverManager.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException((e));
        }
        testStand = properties.getProperty("test.stand");
    }

    public static String getStand() {
        return testStand;
    }

    /**
     * Метод для получения скриншота и сохранения его в папке screenShots
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    protected void screenShotStep() {
        TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
        File screen = ts.getScreenshotAs(OutputType.FILE);
        String screenName = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String className = this.getClass().getSimpleName();
        try {
            FileUtils.copyFile(screen, new File("./ScreenShots/" + className + "/" + screenName + "_Screenshot.png"));
        } catch (IOException e) {
            System.out.println("Exception while taking ScreenShot "+e.getMessage());
            e.printStackTrace();
        }
    }

    protected void checkAndScreenShot (String nameStep, boolean check, String message) {
        step(nameStep, () -> {
            Assert.assertTrue(check, message);
        });
        screenShotStep();
    }


}
