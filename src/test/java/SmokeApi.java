import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class SmokeApi {

    @Test(description = "API. count labels")
    public void getAll() {
        GetSettings.get();
        Api api = new Api(GetSettings.API, GetSettings.APIKEY, GetSettings.APITOKEN);
        //TODO need some preparation suite
        assertTrue(api.getLabelApi().getAllLabelIds().size() > 0, "Configuration is expected");
    }

    @Test(description = "API. delete label")
    public void delete() {
        GetSettings.get();
        Api api = new Api(GetSettings.API, GetSettings.APIKEY, GetSettings.APITOKEN);
        ArrayList<String> labels = api.getLabelApi().getAllLabelIds();
        String id = labels.get(0);
        api.getLabelApi().deleteLabel(id);
        ArrayList<String> labelsAfter = api.getLabelApi().getAllLabelIds();
        assertTrue(labelsAfter.size() == labels.size() - 1, "Label is deleted");
        assertFalse(labelsAfter.contains(id), "Expected label is deleted");
    }

    @Test(description = "API. delete label. Negative")
    public void deleteNegative() {
        GetSettings.get();
        Api api = new Api(GetSettings.API, GetSettings.APIKEY, GetSettings.APITOKEN, false);
        ArrayList<String> labels = api.getLabelApi().getAllLabelIds();
        String id = "NOT_ID";
        Response response = api.getLabelApi().deleteLabel(id);
        assertEquals("Expected label is not deleted",response.getStatusCode(), 400 );
    }

    @Test(description = "API. modify label")
    public void modify() {
        final String NEW_NAME = "test";
        GetSettings.get();
        Api api = new Api(GetSettings.API, GetSettings.APIKEY, GetSettings.APITOKEN);
        ArrayList<String> labels = api.getLabelApi().getAllLabelIds();
        String id = labels.get(0);
        api.getLabelApi().putLabel(id, NEW_NAME);
        Response test = api.getLabelApi().getLabel(id);
        assertTrue(NEW_NAME.equals(test.jsonPath().get("name")),  "Label name is expected");
    }

    @Test(description = "API. create label")
    public void create() {
        final String NEW_COLOR = "red";
        final String NEW_NAME = "test" + Math.random();
        GetSettings.get();
        Api api = new Api(GetSettings.API, GetSettings.APIKEY, GetSettings.APITOKEN);
        ArrayList<String> labels = api.getLabelApi().getAllLabelIds();
        api.getLabelApi().createLabel(NEW_NAME, NEW_COLOR);
        ArrayList<String> labelsAfter = api.getLabelApi().getAllLabelIds();
        assertTrue(labelsAfter.size() == labels.size() + 1, "Label is created");
    }


}
