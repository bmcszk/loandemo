# Loan demo

### To build and test:

`./gradlew clean build test`

### To run the app on 8080 port:

`./gradlew bootRun`

### To create new loan:

`curl --location --request POST 'localhost:8080/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": 6000,
    "period": 25,
    "created": "2020-01-01T00:00:00"
}'`

### To extend the loan period (put correct uuid):

`curl --location --request PATCH 'localhost:8080/3affcf34-47a7-42a3-9a9f-2a310dd2f004'`
