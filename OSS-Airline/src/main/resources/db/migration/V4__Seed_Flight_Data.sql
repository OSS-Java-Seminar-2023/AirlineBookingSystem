-- Create a stored procedure
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
    (SELECT id FROM airport ORDER BY RAND() LIMIT 1),
            (SELECT id FROM airport ORDER BY RAND() LIMIT 1),
            (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
            currentDate,
            '08:00:00',
            'GateA',
            '04:30:00';

-- Duplicate Flight 2
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU202',
    (SELECT id FROM airport ORDER BY RAND() LIMIT 1),
            (SELECT id FROM airport ORDER BY RAND() LIMIT 1),
            (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
            currentDate,
            '10:30:00',
            'GateB',
            '03:15:00';

-- Duplicate Flight 3
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU303',
    (SELECT id FROM airport ORDER BY RAND() LIMIT 1),
            (SELECT id FROM airport ORDER BY RAND() LIMIT 1),
            (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
            currentDate,
            '14:45:00',
            'GateC',
            '02:45:00';

-- Duplicate Flight 4
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU404',
    (SELECT id FROM airport ORDER BY RAND() LIMIT 1),
            (SELECT id FROM airport ORDER BY RAND() LIMIT 1),
            (SELECT id FROM airplane ORDER BY RAND() LIMIT 1),
            currentDate,
            '18:30:00',
            'GateD',
            '05:00:00';

-- Increment date for the next iteration
SET currentDate = DATE_ADD(currentDate, INTERVAL 1 DAY);
END WHILE;
END //

DELIMITER ;

-- Call the stored procedure
CALL InsertFlights();
