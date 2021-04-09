package model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SmsAnalyzeCampaign {
	 BodyAnalysis bodyAnalysis;
	 RecipientsPerCountry recipientsPerCountry;
	 int totalInGroups;
	 RecipientCountries recipientCountries;
	 Contacts contacts;
	 RecipientsPerGroup recipientsPerGroup;
	 int numberOfRecipients;
}
