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
    a1.id AS from_id,
    a2.id AS to_id,
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
            currentDate,
            '08:00:00',
            'GateA',
            '04:30:00'
FROM airport a1, airport a2
WHERE a1.id <> a2.id
ORDER BY RAND()
    LIMIT 1;

-- Duplicate Flight 2
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU202',
    a1.id AS from_id,
    a2.id AS to_id,
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
            currentDate,
            '10:30:00',
            'GateB',
            '03:15:00'
FROM airport a1, airport a2
WHERE a1.id <> a2.id
ORDER BY RAND()
    LIMIT 1;

-- Duplicate Flight 3
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU303',
    a1.id AS from_id,
    a2.id AS to_id,
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
            currentDate,
            '14:45:00',
            'GateC',
            '02:45:00'
FROM airport a1, airport a2
WHERE a1.id <> a2.id
ORDER BY RAND()
    LIMIT 1;

-- Duplicate Flight 4
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
SELECT
    UUID(),
    'OU404',
    a1.id AS from_id,
    a2.id AS to_id,
    (SELECT id FROM airplane ORDER BY RAND() LIMIT 1) AS airplane_id,
            currentDate,
            '18:30:00',
            'GateD',
            '05:00:00'
FROM airport a1, airport a2
WHERE a1.id <> a2.id
ORDER BY RAND()
    LIMIT 1;

-- Increment date for the next iteration
SET currentDate = DATE_ADD(currentDate, INTERVAL 1 DAY);
END WHILE;
END //

DELIMITER ;

    call InsertFlights();