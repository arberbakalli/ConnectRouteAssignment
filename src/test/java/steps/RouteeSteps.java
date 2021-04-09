package steps;

import com.google.gson.JsonObject;
import model.MyContacts;
import model.SmsAnalyzeCampaign;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import restAPI.RouteeClient;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteeSteps {

    private Optional<MyContacts> myContacts;
    private List<SmsAnalyzeCampaign> smsAnalyzeCampaign;

    @Steps
    RouteeClient routeeClient;

    public void createValidContact(JsonObject userPayload) {
        routeeClient.createContact(userPayload);
    }

    public void verifyContactCreated() {
        myContacts = Optional.ofNullable(SerenityRest.lastResponse().jsonPath().getObject("", MyContacts.class));
        assertThat(myContacts.isPresent()).isTrue();
        assertThat(myContacts.get().getFirstName()).isNotEmpty();
        assertThat(myContacts.get().getLastName()).isNotEmpty();
        assertThat(myContacts.get().getCountry()).isNotEmpty();
        assertThat(myContacts.get().getMobile()).isNotEmpty();
        assertThat(myContacts.get().getGroups()).isNotEmpty();
        assertThat(myContacts.get().getId()).isNotEmpty();
        assertThat(myContacts.get().getBlacklistedServices()).isEmpty();
        assertThat(myContacts.get().isVip()).isTrue();
        assertThat(myContacts.get().getEmail()).isNotEmpty();
        assertThat(myContacts.get().getLabels()).isEmpty();
    }
}
