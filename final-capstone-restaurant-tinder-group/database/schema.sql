-- *************************************************************************************************
-- This script creates all of the database objects (tables, sequences, etc) for the database
-- *************************************************************************************************

BEGIN;

DROP TABLE IF EXISTS app_user;

CREATE TABLE app_user (
  user_id SERIAL PRIMARY KEY,
  username varchar(32) NOT NULL UNIQUE,
  password varchar(32) NOT NULL,
  salt varchar(255) NOT NULL,
  address varchar (200) NOT NULL,
  mile_range integer NOT NULL
  
);

DROP TABLE IF EXISTS preferences;

CREATE TABLE preferences(
    preference_id SERIAL PRIMARY KEY,
    preference varchar(50) NOT NULL       
);

DROP TABLE IF EXISTS users_preferences;

CREATE TABLE users_preferences(
     user_id integer NOT NULL,
     preference_id integer NOT NULL,
     FOREIGN KEY (user_id) REFERENCES app_user (user_id),
     FOREIGN KEY (preference_id) REFERENCES preferences (preference_id)
);

DROP TABLE IF EXISTS restaurants;
CREATE TABLE restaurants(
        res_id varchar (40) NOT NULL,
        res_name varchar(60) NOT NULL,
        photo_url varchar(300),
        cuisines varchar(80) NOT NULL,
        display boolean,
        address varchar(100) NOT NULL,
        latitude varchar (80) NOT NULL,
        longitude varchar (80) NOT NULL
        
);

DROP TABLE IF EXISTS users_favorites;
CREATE TABLE users_favorites(
        user_id integer NOT NULL,
        res_id varchar (80) NOT NULL,
        FOREIGN KEY (user_id) REFERENCES app_user (user_id)
       
);


DELETE FROM restaurants;

COMMIT;