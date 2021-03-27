-- Name:        Scott Alton
-- Created On:  January 26, 2021
-- Description: This file creates faculty table that contains faculty member info.

DROP TABLE IF EXISTS Faculty;

CREATE TABLE Faculty (
    id BIGINT PRIMARY KEY,
    school_code VARCHAR(4),
    school_description VARCHAR(200),
    office VARCHAR(8),
    extension INTEGER,
    FOREIGN KEY(id) REFERENCES users(id)
);

-- Create 3 new student users
INSERT INTO Faculty VALUES (
        100454345,
        'BITM',
        'School of Business, IT & Management',
        '122A',
        23
        );

INSERT INTO Faculty VALUES (
        100090945,
        'BITM',
        'School of Business, IT & Management',
        '204B',
        27
        );

INSERT INTO Faculty VALUES (
        100565454,
        'BITM',
        'School of Business, IT & Management',
        '202A',
        24
        );
