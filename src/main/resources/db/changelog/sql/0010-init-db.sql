CREATE TABLE MORTGAGE_RATES
(
MORTGAGE_RATES_ID BIGINT NOT NULL,
MATURITY_PERIOD INTEGER,
INTEREST_RATE  DECIMAL,
LAST_UPDATED timestamp,
PRIMARY KEY (MORTGAGE_RATES_ID));

CREATE TABLE MORTGAGE_CHECKS
(
MORTGAGE_CHECKS_ID BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
INCOME DECIMAL,
MATURITY_PERIOD INTEGER,
LOAN_VALUE DECIMAL,
HOME_VALUE  DECIMAL,
LOAN_GRANTED VARCHAR,
PRIMARY KEY (MORTGAGE_CHECKS_ID));

INSERT INTO MORTGAGE_RATES(MORTGAGE_RATES_ID,MATURITY_PERIOD,INTEREST_RATE,LAST_UPDATED) VALUES(1,2,4,'2023-06-04');
INSERT INTO MORTGAGE_RATES(MORTGAGE_RATES_ID,MATURITY_PERIOD,INTEREST_RATE,LAST_UPDATED) VALUES(2,2,4,'2023-06-04');
