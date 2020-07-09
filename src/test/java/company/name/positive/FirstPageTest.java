package company.name.positive;
import company.name.pages.FirstPage;
import org.testng.annotations.Test;

public class FirstPageTest {

    FirstPage step = new FirstPage();

    @Test
    public void checkFirstPageTest(){
        step.openTestStand();
        step.openWantTestSitePage();
        step.openFAQPage();
        step.openPricesPage();
        step.openAbouteProductPage();
    }
}
