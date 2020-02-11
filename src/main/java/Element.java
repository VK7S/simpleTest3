
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Element {

    protected By xpath;
    public String getXpath(){
        return xpath.toString();
    }

    public Element(String locator) {
        xpath = By.xpath(locator);
    }

    public Element(String locator, String value) {
        xpath = By.xpath(String.format(locator, value));
    }


    public Element(Element parent, String locator) {
        xpath = By.xpath(parent.getXpath().concat(locator));
    }

    public Element(Element parent, String locator, String value) {
        xpath = By.xpath(parent.getXpath().concat(String.format(locator, value)));
    }

    public void waitToAppear(){
        SeleniumWrapper.getInstance().getWait().until(presenceOfElementLocated(xpath));
    }

    public void waitToClickable(){
        SeleniumWrapper.getInstance().getWait().until(elementToBeClickable(xpath));
    }

    public void waitToDisappear(){
        SeleniumWrapper.getInstance().getWait().until(ExpectedConditions.invisibilityOfElementLocated(xpath));
    }

    public boolean isPresent(){
        return SeleniumWrapper.getInstance().getDriver().findElements(xpath).size() > 0;
    }

    public boolean isDisplayed(){
        return SeleniumWrapper.getInstance().getDriver().findElement(xpath).isDisplayed();
    }

    public boolean isEnabled(){
        return SeleniumWrapper.getInstance().getDriver().findElement(xpath).isEnabled();
    }

    public void isReadyToWrite(){
        if(!this.isPresent())
            throw new TestException(String.format("Element %s is not present", this.getXpath()));
        if(!this.isEnabled())
            throw new TestException(String.format("Element %s is not enabled", this.getXpath()));
    }

    public void click(){
        if(!this.isPresent())
            throw new TestException(String.format("Element %s is not present", this.getXpath()));
        SeleniumWrapper.getInstance().getDriver().findElement(xpath).click();
    }

    public String getText(){
        if(!this.isPresent())
            throw new TestException(String.format("Element %s is not present", this.getXpath()));
        return SeleniumWrapper.getInstance().getDriver().findElement(xpath).getText();
    }




}
