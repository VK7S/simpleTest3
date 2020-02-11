import org.testng.Reporter;

public class LoginPage {



    public LoginPage() {
    }

    public void waitPageLoad(){
        userInput.waitToAppear();
    }

    private Button signInButton = new Button("//input[@id='login']");
    private Button submitButton = new Button("//button[@id='login-submit']");
    private Input userInput    = new Input("//input[@id='user']");
    private Input passInput    = new Input("//input[@id='password']");
    private Element error      = new Element("//div[@id='error' and @class='quick-switch']/p");
    private Element alert      = new Element("//div[@id='login-error']/span");

    public LoginPage setUser(String user){
        System.out.println("Fill username");
        userInput.waitToAppear();
        userInput.sendKeys(user);
        return this;
    }

    public LoginPage setPass(String pass){
        System.out.println("Fill password");
        passInput.waitToAppear();
        passInput.sendKeys(pass);
        return this;
    }

    public LoginPage clickLogIn(){
        System.out.println("Click on login button");
        signInButton.waitToAppear();
        signInButton.waitToClickable();
        signInButton.click();
        return this;
    }

    public LoginPage clickSubmit(){
        System.out.println("Click on submit button");
        submitButton.waitToAppear();
        submitButton.waitToClickable();
        submitButton.click();
        return this;
    }


    public MainPage logIn(String user, String pass){
        waitPageLoad();
        setUser(user);
        clickLogIn();
        clickLogIn();
        clickSubmit();
        setPass(pass);
        clickSubmit();
        return new MainPage();

    }

    public String getError(){
        error.waitToAppear();
        System.out.println(error.getText());
        return error.getText();
    }

    public boolean isAlertPresent(){
        return alert.isPresent();
    }

    public String getAlertMessage(){
        return alert.getText();
    }

}
