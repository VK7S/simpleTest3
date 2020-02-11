import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class DefaultTest {


    @BeforeClass
    public void setUp() {
        GetSettings.get();
        System.setProperty("webdriver.chrome.driver", GetSettings.CHROME_PATH);
        SeleniumWrapper.getInstance().setDriver(new ChromeDriver());
        SeleniumWrapper.getInstance().setWait(new WebDriverWait(SeleniumWrapper.getInstance().getDriver(), 10));
        SeleniumWrapper.getInstance().getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        SeleniumWrapper.getInstance().getDriver().manage().window().maximize();


    }

    @BeforeMethod
    public void setMethod() {
        SeleniumWrapper.getInstance().getDriver().get(GetSettings.URL);

    }

    @AfterClass
    public void tearDown() {
        SeleniumWrapper.getInstance().getDriver().quit();
    }
}
