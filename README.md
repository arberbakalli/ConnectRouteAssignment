<h1 align="center">Connect Route Assignment</h1>

##📜 Notes
<h3>1. Using Routee API as reference</h3> 
[Endpoints Reference](https://docs.routee.net/reference)
 
a) Create a contact the issue is that the json attributes were wrong

`Actual json data, vip should be true/false && mobiles should be mobile. Also the country was missing`
```json
{
"firstName": "Bob",
"lastName": "John",
"mobiles": "+306984512555",
"vip": "Yes" ,
"email" : "bob@example.com"
}
```

`Expected json data`
```json
{ 
"firstName": "Bob", 
"lastName": "John", 
"mobile": "+306984512555", 
"country":"gr",
"vip": "true" , 
"email" : "bob@example.com"
 }
```
---
b) Analyze an SMS campaign

`Actual json data, url is wrong "Analyse" should be "Analyze"  "https://connect.routee.net/sms/analyse/campaign"`
`Json data is missing contracts and to`
```json
{
  "body": "Service 003 is offline",
  "groups" : [ "Admins" ],
  "from": "Routee"
}
```

`Expected json data, correct url https://connect.routee.net/sms/analyze/campaign`
```json
{
	"contacts":[],
	"groups" : [ "Admins" ],
	"to":["+306948530920 "],
	"from": "Routee",
"body": "Service 003 is offline"
 }
```
***
<h3>2. Using OTRS DB schema as reference </h3>
[DB Schema](https://ftp.otobo.org/pub/otrs/doc/database-schema/otrs-6-database.png)





## 🚀 Installation
To run the automation project, you should run ```mvn verify``` from the command line or

```shell
mvn clean install
```

## 🔥 Executing the tests
To execute the entire set of tests write on the command promp

```shell
mvn test
```

The test results will be recorded in the `target/site/serenity` directory.

## 📁 File structure

Assignment

```
.idea/
src/
├─ main/
│  ├─ java/
│  │  ├─ controller/
│  │  ├─ AuthenticationController.java
│  │  ├─ EnvironmentController.java
│  │
│  │  ├─ model/
│  │  ├─ Authentication.java
│  │  ├─ BodyAnalysis.java
│  │  ├─ Contracts.java
│  │  ├─ Environment.java
│  │  ├─ MyContracts.java
│  │  ├─ RecipientCountries.java
│  │  ├─ RecipientsPerCountry.java
│  │  ├─ RecipientsPerGroup.java
│  │  ├─ SmsAnaylyzeCampaign.java
│  │  ├─ User.java
│  │
│  │  ├─ restAPI/
│  │  │  ├─ Endpoints.enum
│  │  │  ├─ RouteeClient.java

│  │  ├─ resources/
│  │  │  ├─ validContact.json
│  │  │  ├─ validSmsCampaign.json
├─ test/
│  ├─ java/
│  │  ├─ stepdefinitions/
│  │  │  ├─ RouteeStepDef.java
│  │  ├─ steps/
│  │  │  ├─ RouteeSteps.java
│  │  ├─ CucumberTestSuite.java
│  │
│  ├─ resources/
│  │  ├─ features/
│  │  │  ├─ Routee.feature
│  │  ├─ routee.prod.json
│  │  ├─ logback-test.xml
.gitattributes
.gitignore
pom.xml
README.md
ConnectRouteAssignment.iml
serenity.properties
```
## 🚚 Roadmap

[Homepage](https://go.routee.net/#/management/dashboard)

Todo add the roadmap to get full automation in an reasonable est time

[comment]: <> (Todo)
[comment]: <> (##Code Example)
[comment]: <> (##Code style)
[comment]: <> (## Build status)
[comment]: <> (##Features)
[comment]: <> (##API Reference)
[comment]: <> (##Screenshots)