DROP TABLE IF EXISTS rental_event;
DROP TABLE IF EXISTS boat;
DROP TABLE IF EXISTS customer;

CREATE TABLE boat (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  brand VARCHAR(255),
  length DOUBLE NOT NULL,
  model VARCHAR(255),
  build_year INT NOT NULL,
  daily_rate DOUBLE PRECISION NOT NULL,
  available BOOLEAN NOT NULL,
  number_of_seats INT NOT NULL,
  version BIGINT NOT NULL default 0,
  CONSTRAINT pk_boat PRIMARY KEY (id)
);


CREATE TABLE customer
(
    id                    INT NOT NULL,
    first_name            VARCHAR(255),
    last_name             VARCHAR(255),
    email                 VARCHAR(255),
    phone_number          VARCHAR(255),
    driver_license_number VARCHAR(255),
    country_code          VARCHAR(255),
    CONSTRAINT pk_customer PRIMARY KEY (id)
);

CREATE TABLE rental_event
(
    id                 INT              NOT NULL,
    boat_rented_id      BIGINT,
    rental_customer_id  INT,
    rental_date         DATE,
    return_date         DATE,
    total_cost          DOUBLE PRECISION NOT NULL,
    is_closed           BOOLEAN          NOT NULL,
    CONSTRAINT pk_rental_event PRIMARY KEY (id),
    CONSTRAINT fk_boat FOREIGN KEY (boat_rented_id) REFERENCES boat(id),
    CONSTRAINT fk_customer FOREIGN KEY (rental_customer_id) REFERENCES customer(id)
);
