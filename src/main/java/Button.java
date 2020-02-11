public class Button extends Element {
    public Button(String locator) {
        super(locator);
    }

    public Button(Element parent, String locator) {
        super(parent, locator);
    }

    public Button(String locator, String value) {
        super(locator, value);
    }

    public Button(Element parent, String locator, String value) {
        super(parent, locator, value);
    }
}
