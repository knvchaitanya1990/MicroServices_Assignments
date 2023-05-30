

DROP TABLE IF EXISTS CENTRAL_INSURANCE;

CREATE TABLE CENTRAL_INSURANCE (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  REGISTRATION_NUMBER VARCHAR(250) NOT NULL,
  ENGINE_NUMBER VARCHAR(250) NOT NULL,
  POLICY_START_DATE date NOT NULL,
  POLICY_END_DATE date NOT NULL,
  COMPLETE_POLICY_COVERAGE  boolean NOT NULL,
  CUSTOMER_NAME VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(40) DEFAULT NULL,
  CONTACT_NUMBER VARCHAR(40) DEFAULT NULL,
  INSURANCE_PROVIDER VARCHAR(50) NOT NULL
);