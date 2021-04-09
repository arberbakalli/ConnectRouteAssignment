package controller;

import model.Authentication;
import model.User;
import net.serenitybdd.rest.SerenityRest;

import java.util.Base64;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anything;

public class AuthenticationController {

    private static String authentication_url;
    private static Optional<Authentication> authentications;

    public static void setAuthentication_url() {
        authentication_url = EnvironmentController.getEnvironment().getAuthentication_url();
    }

    public static String authenticateWith(String type) {
        SerenityRest.given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", authenticateBasic(type))
                .queryParam("grant_type", "client_credentials")
                .post(authentication_url);
        verifyBasicAuth();
        return String.format("Bearer %s", authentications.get().getAccess_token());
    }

    private static void verifyBasicAuth() {
        authentications = Optional.ofNullable(SerenityRest.lastResponse().jsonPath().getObject("", Authentication.class));
        assertThat("" + authentications.isPresent(), true);
        assertThat(authentications.get().getAccess_token(), anything());
        assertThat(authentications.get().getToken_type(), anything());
        assertThat(authentications.get().getExpires_in(), anything());
        assertThat(authentications.get().getPermissions(), anything());
        assertThat(authentications.get().getScope(), anything());
    }

    private static String authenticateBasic(String type) {
        User admin = EnvironmentController.getUserById(type);
        String byteArray = admin.getApplication_id() + ":" + admin.getApplication_secret();
        return String.format("Basic %s", Base64.getEncoder().encodeToString(byteArray.getBytes()));
    }
}
