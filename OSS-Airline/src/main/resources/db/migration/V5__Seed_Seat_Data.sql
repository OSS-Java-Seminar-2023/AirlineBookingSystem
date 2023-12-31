DELIMITER //

CREATE PROCEDURE GenerateSeatsForFlights()
BEGIN
    DECLARE airplaneIdVar VARCHAR(36);
    DECLARE firstClassSeats INT;
    DECLARE businessClassSeats INT;
    DECLARE economyClassSeats INT;
    DECLARE flightIdVar VARCHAR(36);
    DECLARE doneFlight BOOLEAN DEFAULT FALSE;

    -- Cursor to iterate through flights
    DECLARE flightCursor CURSOR FOR
SELECT id, airplane_id
FROM flight;

-- Declare handlers
DECLARE CONTINUE HANDLER FOR NOT FOUND
        SET doneFlight = TRUE;

OPEN flightCursor;

flightLoop: LOOP
        FETCH flightCursor INTO flightIdVar, airplaneIdVar;

        IF doneFlight THEN
            LEAVE flightLoop;
END IF;

        -- Retrieve the number of seats for the associated plane
SELECT first_seats, business_seats, economy_seats
INTO firstClassSeats, businessClassSeats, economyClassSeats
FROM airplane
WHERE id = airplaneIdVar;

-- Insert seats for each class and associate with the current flight
INSERT INTO seat (id, flight_id, seat_number, seat_price, seat_class)
SELECT UUID(), flightIdVar, CONCAT('F', ROW_NUMBER() OVER ()), 100.00, 'First Class'
FROM information_schema.tables
         LIMIT firstClassSeats;

INSERT INTO seat (id, flight_id, seat_number, seat_price, seat_class)
SELECT UUID(), flightIdVar, CONCAT('B', ROW_NUMBER() OVER ()), 50.00, 'Business Class'
FROM information_schema.tables
         LIMIT businessClassSeats;

INSERT INTO seat (id, flight_id, seat_number, seat_price, seat_class)
SELECT UUID(), flightIdVar, CONCAT('E', ROW_NUMBER() OVER ()), 30.00, 'Economy Class'
FROM information_schema.tables
         LIMIT economyClassSeats;

END LOOP;

CLOSE flightCursor;
END //

DELIMITER ;

CALL GenerateSeatsForFlights();
