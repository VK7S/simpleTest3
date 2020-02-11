import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWrapper {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    private WebDriverWait wait;


        private static volatile SeleniumWrapper instance;

        public static SeleniumWrapper getInstance() {
            SeleniumWrapper localInstance = instance;
            if (localInstance == null) {
                synchronized (SeleniumWrapper.class) {
                    localInstance = instance;
                    if (localInstance == null) {
                        instance = localInstance = new SeleniumWrapper();
                    }
                }
            }
            return localInstance;
        }

}
