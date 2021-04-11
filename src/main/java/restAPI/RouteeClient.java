package restAPI;

import com.google.gson.JsonObject;
import controller.AuthenticationController;
import controller.EnvironmentController;
import net.serenitybdd.rest.SerenityRest;

public class RouteeClient {

    private final String baseURL = EnvironmentController.getEnvironment().getBase_url();
    private String bearerToken;

    RouteeClient() {
        AuthenticationController.setAuthentication_url();
        bearerToken = AuthenticationController.authenticateWith("admin_arber");
    }

    public void createContact(JsonObject data) {
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", bearerToken)
                .body(data)
                .post(baseURL + Endpoints.CONTACTS.getPath());
    }

    public void createSmsCampaign(JsonObject data) {
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", bearerToken)
                .body(data)
                .post(baseURL + Endpoints.SMSCAMPAIGN.getPath());
    }
}
