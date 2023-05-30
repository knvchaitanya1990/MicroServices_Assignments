

DROP TABLE IF EXISTS VEHICLE_THEFT_DB;

CREATE TABLE VEHICLE_THEFT_DB (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  REGISTRATION_NUMBER VARCHAR(250) NOT NULL,
  CUSTOMER_NAME VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) DEFAULT NULL
);
