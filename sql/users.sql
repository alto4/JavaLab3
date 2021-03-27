-- Name:        Scott Alton
-- Created On:  January 19, 2021
-- Description: This file creates a users table that uses password encryption and contains user login and contact info.
--              6 records are initially generated, including 3 faculty members and 3 students.

CREATE EXTENSION IF NOT EXISTS pgcrypto;

DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    id BIGINT PRIMARY KEY,
    password VARCHAR(40) NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    email_address VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT false,
    type CHAR(1) NOT NULL,
    enrol_date TIMESTAMP  NOT NULL,
    last_access TIMESTAMP NOT NULL
);

-- Create 3 new faculty users
INSERT INTO Users VALUES (
        100454345,
        ENCODE(DIGEST('password', 'sha1'), 'hex'),
        'Myles',
        'Mapman',
        'myles.mapman@dcmail.ca',
        'true',
        'f',
        '2021-01-19 9:00',
        '2021-01-19 9:00'
        );

INSERT INTO Users VALUES (
        100090945,
        ENCODE(DIGEST('password', 'sha1'), 'hex'),
        'Sal',
        'McSalsa',
        'sal.mcsalsa@dcmail.ca',
        'true',
        'f',
        '2021-01-19 9:00',
        '2021-01-19 9:00'
        );

INSERT INTO Users VALUES (
        100565454,
        ENCODE(DIGEST('password', 'sha1'), 'hex'),
        'Dave',
        'Davis',
        'dave.davis@dcmail.ca',
        'true',
        'f',
        '2021-01-19 9:00',
        '2021-01-19 9:00'
        );

-- Create 3 new student users
INSERT INTO Users VALUES (
        100762638,
        ENCODE(DIGEST('password', 'sha1'), 'hex'),
        'Scott',
        'Alton',
        'scott.alton@dcmail.ca',
        'true',
        's',
        '2021-01-19 9:00',
        '2021-01-19 9:00'
        );

INSERT INTO Users VALUES (
        100111111,
        ENCODE(DIGEST('password', 'sha1'), 'hex'),
        'Mike',
        'Jones',
        'mike.jones@dcmail.ca',
        'true',
        's',
        '2021-01-19 9:50',
        '2021-01-19 9:50'
        );

INSERT INTO Users VALUES (
        100982323,
        ENCODE(DIGEST('password', 'sha1'), 'hex'),
        'James',
        'Jamerson',
        'james.jamerson@dcmail.ca',
        'true',
        's',
        '2021-01-19 9:50',
        '2021-01-19 9:50'
        );