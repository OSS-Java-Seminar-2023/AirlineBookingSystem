DELIMITER //

CREATE PROCEDURE InsertFlights()
BEGIN
    -- Variables
    DECLARE startDate DATE;
    DECLARE endDate DATE;
    DECLARE currentDate DATE;

    SET startDate = '2024-01-01';
    SET endDate = '2024-12-31';
    SET currentDate = startDate;

    -- Loop through each day
    WHILE currentDate <= endDate DO
        -- Duplicate Flight 1
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU101',
    (SELECT id FROM airport WHERE name = 'Dubrovnik Airport'),
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
    currentDate,
    '08:00:00',
    'GateB',
    '08:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU202',
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
    currentDate,
    '08:05:00',
    'GateC',
    '09:55:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU303',
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
    currentDate,
    '10:50:00',
    'GateA',
    '12:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU404',
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
    currentDate,
    '15:00:00',
    'GateG',
    '16:55:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU505',
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
    currentDate,
    '18:20:00',
    'GateA',
    '20:15:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU606',
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airport WHERE name = 'London Heathrow Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
    currentDate,
    '11:40:00',
    'GateD',
    '14:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU606',
    (SELECT id FROM airport WHERE name = 'London Heathrow Airport'),
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
    currentDate,
    '16:15:00',
    'GateF',
    '19:30:00';

-- Increment date for the next iteration
SET currentDate = DATE_ADD(currentDate, INTERVAL 1 DAY);
END WHILE;
END //

DELIMITER ;

    call InsertFlights();