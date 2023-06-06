# Mortgage Loan check service

## Running the service locally

In order to run the service locally, you need to provide the following jvm parameter:

...
-Dspring.profiles.active=local
...


## Swagger documentation url
...
http://localhost:8080/swagger-ui/index.html
...

## Actuator:

...
http://localhost:8080/actuator
...

## Input json

{
"income": 30000,
"maturityPeriod": 36,
"loanValue": 50000,
"homeValue": 60000
}
