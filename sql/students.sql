-- Name:        Scott Alton
-- Created On:  January 26, 2021
-- Description: This file creates a students table that contains student info.

DROP TABLE IF EXISTS Students;

CREATE TABLE Students (
    id BIGINT PRIMARY KEY,
    program_code VARCHAR(4),
    program_description VARCHAR(500),
    year INTEGER,
    FOREIGN KEY(id) REFERENCES users(id)
);

-- Create 3 new student users
INSERT INTO Students VALUES (
        100762638,
        'CPA',
        'Computer Programming and Analysis',
        2
        );

INSERT INTO Students VALUES (
        100111111,
        'CST',
        'Computer Systems Technology',
        3
        );

INSERT INTO Students VALUES (
        100982323,
        'CET',
        'Civil Engineering Technology',
        1
        );
