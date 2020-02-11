import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Reporter;
import org.testng.TestException;

import java.util.ArrayList;

import static io.restassured.config.EncoderConfig.encoderConfig;


public class Api {

    private String url;
    private String key;
    private String token;
    private String defaultBoard = "5e426fede097b34ce2d3ed41";
    private boolean catchErrors = true;

    public Label getLabelApi() {
        return labelApi;
    }

    private Label labelApi;

    public Api(String url, String key, String token) {
        this.url = url;
        RequestSpecification request = RestAssured.given();
        this.key = key;
        this.token = token;
        labelApi = new Label();
    }

    public Api(String url, String key, String token, boolean catchErrors) {
        this.url = url;
        RequestSpecification request = RestAssured.given();
        this.key = key;
        this.token = token;
        labelApi = new Label();
        this.catchErrors = catchErrors;     //avoids to turn off response wrapper
    }

    private RequestSpecification prepareRequest() {
        RestAssured.baseURI = url;
        //rest assured adds header by default, this is not expected and do not allowed for post request with params
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RequestSpecification request = RestAssured.given()
                .urlEncodingEnabled(false)                  //turn off encoding for post request
                .log().uri().log().method().log().params(); //bad logging is better than nothing
        request.param("token", token)                    //add credentials
                .param("key", key);
        return request;
    }

    /**
     * Response wrapper throws exceptions for not expected response codes
     * It can be switched off for negative cases
     * In this project expected assertions are not used because loosing negative cases
     */
    public Response validateResponse(Response response) {
        System.out.println(String.format("Response code:    %s\n", response.getStatusCode()));
        if (!catchErrors)
            return response;
        if (response.getStatusCode() / 100 != 2) {
            throw new TestException(String.format("Response code: %s \nException: %s",response.getStatusLine(), response.asString()));
        }
        return response;
    }

    /*
     * Labels api
     */
    public class Label {

        public ArrayList<String> getAllLabelIds() {
            return getAllLabels().getBody().jsonPath().get("id");
        }

        //GET Nested Resource
        public Response getAllLabels() {
            return validateResponse(prepareRequest()
                    .when()
                    .basePath(String.format("boards/%s/labels", defaultBoard))
                    .get());
        }

        // GET
        public Response getLabel(String id) {
            return validateResponse(prepareRequest()
                    .when()
                    .basePath(String.format("boards/%s/labels/%s", defaultBoard, id))
                    .get());
        }

        //PUT
        public Response putLabel(String id, String name) {
            return validateResponse(prepareRequest().when()
                    .basePath("labels/" + id)
                    .param("name", name)
                    .put());
        }

        //DELETE
        public Response deleteLabel(String id) {
            return validateResponse(prepareRequest()
                    .when()
                    .basePath("/labels/" + id)
                    .delete());
        }

        //POST
        public Response createLabel(String name, String color) {
            return validateResponse(prepareRequest().when().basePath("labels")
                    .param("name", name)
                    .param("color", color)
                    .param("idBoard", defaultBoard)
                    .post());
        }

    }


}
