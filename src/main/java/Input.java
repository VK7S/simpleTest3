public class Input extends Element {
    public Input(String locator) {
        super(locator);
    }

    public Input(Element parent, String locator) {
        super(parent, locator);
    }

    public void sendKeys(String data) {
        isReadyToWrite();
        SeleniumWrapper.getInstance().getDriver().findElement(xpath).sendKeys(data);
    }

    public Input(String locator, String value) {
        super(locator, value);
    }

    public Input(Element parent, String locator, String value) {
        super(parent, locator, value);
    }
}
