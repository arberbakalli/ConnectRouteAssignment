<h1 align="center">Connect Route Assignment</h1>

---
## 📜 Notes

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

a) Find tickets created the previous day
```sql
SELECT * FROM ticket WHERE create_date  >= DATEADD
    (day, -1, CAST(CAST(GETDATE() AS DATE) AS DATETIME)) 
   and create_date  <=  <  CAST(CAST(GETDATE() AS DATE) AS DATETIME);
```
* Since the datatype of the create_date column is DATETIME We need to remove the hours, minutes, seconds, and milliseconds and make them zero to get the format: "YYYY-MM-DD 00:00:000".
  To query all the tickets that were created yesterday, we need to have the time interval from yesterday midnight zero hours, seconds, minutes, and milliseconds.
  For example, let us assuming yesterday was the 10th of APRIL it would start from 2021-04-10 00:00:000 and end at (including) 2021-04-10 23:59:999 or 2021-04-11 00:00:000 minus 1 millisecond.
  We take today's date, cast it to DATE so they become zero, and typecast it again to DATETIME to get the remaining time as zeros in order to compare DATETIME with DATETIME.


  b) Find tickets last modified by users in user group "QA Testers"

```sql
b)
select * from (
select queue.id from queue
inner join groups
where queue.group_id=groups.id
and groups.name='QA Testers'
) as sub
cross join tickets
where sub.id = ticket.queue_id
order by change_time desc;
```
* This is a nested query that requires to use 'queue' and 'groups' where queue.id is equaled to groups.id and groups.name equals 'QA Testers'
  We treat the result of that query as sub imagine that sub is a table now that has the column id and then we cross join the result with the ticket table where sub.id equals ticket.queue_id.
  The result is all the tickets that were changed by the group that was 'QA Testers'
  Ordering them by Descending also let's us see the latest ticket changes on top of the query

c) Find articles created in the previous month by users in user group "Support"
```sql
select * from articles a
left join ticket t
left join
(select u.id from users u
left join group_users gu
left join groups g
where u.id = gu.id 
and gu.group_id = g.id
and g.name = 'Support') as u
where a.ticket_id = t.id
and t.user_id = u.id
and a.create_time >= DATEADD(month, DATEDIFF(month, 0, GETDATE())-1, 0) /*start of last month*/
and a.create_time < DATEADD(month, DATEDIFF(month, 0, GETDATE()), 0) /*start of this month*/
order by a.create_time desc;
```
Similar to b)

---

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


In order to achieve a fully automated test coverage of [Endpoints Reference](https://docs.routee.net/reference)
in a reasonable amount of time, I would estimate that it can take up to a quarter (3 months time) if the scenarios would
be well documented, and the scope of work is reasonably defined in terms of time per assignment, size of the feature,
reliability of the tests.

Thus the resource planning would be within the automation scope

**Drawbacks?**

If any backend API would require to change in behavior or the format that would increase the scope of current tests being
automated which would creep to upcoming sprints and inevitably require more time to finish a full test coverage.

There is also the posibility of new Feature implementations that might slow down because the work or any other extra refactoring.

**How to get there?**

Time to value. Focus on the important thing and unless it's critical or impacts the core functionality of the software then 
don't invest time on anything but the test automation coverage and core part of the product

[Homepage](https://go.routee.net/#/management/dashboard)


[comment]: <> (Todo)
[comment]: <> (##Code Example)
[comment]: <> (##Code style)
[comment]: <> (## Build status)
[comment]: <> (##Features)
[comment]: <> (##API Reference)
[comment]: <> (##Screenshots)