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
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU101',
    (SELECT id FROM airport WHERE name = 'Dubrovnik Airport'),
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '08:00:00',
    'GateB',
    '00:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU101',
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airport WHERE name = 'Dubrovnik Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '10:00:00',
    'GateB',
    '00:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU101',
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '09:00:00',
    'GateB',
    '00:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU101',
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '12:00:00',
    'GateB',
    '00:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU202',
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '08:05:00',
    'GateC',
    '01:55:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU303',
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '10:50:00',
    'GateA',
    '01:50:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU404',
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '15:00:00',
    'GateG',
    '01:55:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU505',
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '18:20:00',
    'GateA',
    '01:55:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU606',
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airport WHERE name = 'London Heathrow Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '11:40:00',
    'GateD',
    '02:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU606',
    (SELECT id FROM airport WHERE name = 'London Heathrow Airport'),
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '16:15:00',
    'GateF',
    '2:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU202',
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '06:00:00',
    'GateC',
    '01:20:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU303',
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '9:00:00',
    'GateA',
    '01:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU404',
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '12:30:00',
    'GateG',
    '2:00:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU505',
    (SELECT id FROM airport WHERE name = 'Frankfurt Airport'),
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '20:30:00',
    'GateA',
    '1:30:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU332',
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airport WHERE name = 'Barcelona-El Prat Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '07:05:00',
    'GateC',
    '2:05:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU333',
    (SELECT id FROM airport WHERE name = 'Barcelona-El Prat Airport'),
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '10:00:00',
    'GateA',
    '02:05:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU424',
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airport WHERE name = 'Berlin Brandenburg Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '13:00:00',
    'GateG',
    '01:55:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU425',
    (SELECT id FROM airport WHERE name = 'Berlin Brandenburg Airport'),
    (SELECT id FROM airport WHERE name = 'Split Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '16:20:00',
    'GateA',
    '01:55:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU342',
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airport WHERE name = 'Barcelona-El Prat Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '07:05:00',
    'GateC',
    '02:00:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU343',
    (SELECT id FROM airport WHERE name = 'Barcelona-El Prat Airport'),
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '10:00:00',
    'GateA',
    '02:00:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU434',
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airport WHERE name = 'Berlin Brandenburg Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '13:00:00',
    'GateG',
    '01:55:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU435',
    (SELECT id FROM airport WHERE name = 'Berlin Brandenburg Airport'),
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '16:20:00',
    'GateA',
    '01:55:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU454',
    (SELECT id FROM airport WHERE name = 'Zagreb International Airport'),
    (SELECT id FROM airport WHERE name = 'Amsterdam Airport Schiphol'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '14:00:00',
    'GateG',
    '02:20:00';

INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU445',
    (SELECT id FROM airport WHERE name = 'Berlin Brandenburg Airport'),
    (SELECT id FROM airport WHERE name = 'Amsterdam Airport Schiphol'),
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
    currentDate,
    '15:20:00',
    'GateA',
    '01:15:00';

-- Increment date for the next iteration
SET currentDate = DATE_ADD(currentDate, INTERVAL 1 DAY);
END WHILE;
END //

DELIMITER ;

    call InsertFlights();