import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SmokeTests extends DefaultTest {


    private final static String BOARD_NAME = "test";

    @Test(description = "Login with valid credentials")
    public void validLogin() {
        new LoginPage().logIn(GetSettings.USER, GetSettings.PASS);
        assertTrue(new MainPage().isBoardPresent(BOARD_NAME), "List is present!");
    }

    @Test(description = "Login with invalid name")
    public void invalidName() {
        LoginPage page = new LoginPage();
        page.waitPageLoad();
        page.setUser("1234").clickLogIn();
        assertEquals( page.getError(), "There isn't an account for this username","User is invalid");
    }


    @Test(description = "Login with invalid pass")
    public void invalidPass() {
        LoginPage page = new LoginPage();
        page.setUser(GetSettings.USER).clickLogIn().clickLogIn().clickSubmit();     //this steps are needed for page redirecting
        page.setPass("1234").clickSubmit();
        assertTrue(page.isAlertPresent(), "Alert is present");
        assertTrue(page.getAlertMessage().contains("Incorrect email address and / or password."), "Alert is valid");
    }





}
