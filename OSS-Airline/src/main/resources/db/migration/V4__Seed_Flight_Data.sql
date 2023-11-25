-- Flight 1
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
VALUES
    (UUID(), 'OU101', (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airplane ORDER BY RAND() LIMIT 1), '2024-01-01', '08:00:00', 'GateA', '04:30:00');

-- Flight 2
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
VALUES
    (UUID(), 'OU202', (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airplane ORDER BY RAND() LIMIT 1), '2024-01-02', '10:30:00', 'GateB', '03:15:00');

-- Flight 3
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
VALUES
    (UUID(), 'OU303', (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airplane ORDER BY RAND() LIMIT 1), '2024-01-03', '14:45:00', 'GateC', '02:45:00');

-- Flight 4
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
VALUES
    (UUID(), 'OU404', (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airplane ORDER BY RAND() LIMIT 1), '2024-01-04', '18:30:00', 'GateD', '05:00:00');

-- Flight 5
INSERT INTO flight (id, flight_number, from_id, to_id, airplane_id, date, time, gate, duration)
VALUES
    (UUID(), 'OU505', (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airport ORDER BY RAND() LIMIT 1), (SELECT id FROM airplane ORDER BY RAND() LIMIT 1), '2024-01-05', '22:15:00', 'GateE', '03:45:00');
