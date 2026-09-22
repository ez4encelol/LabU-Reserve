-- V5: Seed data migrated from legacy CSV files
-- These are test/demo accounts; passwords are stored in plain text (legacy behavior)

INSERT INTO users (id, user_name, password, email, university_affiliated, verification_num, is_department_approved, user_type) VALUES
    (1, 'jsmith', 'pass123#', 'jsmith@university.ca', TRUE, 100234, TRUE, 'STUDENT'),
    (2, 'adoe', 'alpha321!', 'adoe@university.ca', TRUE, 100235, FALSE, 'STUDENT'),
    (3, 'labtech1', 'secure1!', 'tech1@lab.ca', TRUE, 55012, FALSE, 'RESEARCHER'),
    (4, 'proflee', 'teach789!', 'lee@university.ca', TRUE, 22001, FALSE, 'FACULTY'),
    (5, 'guest01', 'guest111!', 'guest@email.com', FALSE, 0, FALSE, 'GUEST'),
    (6, 'email@gmail.com', 'Password223#', 'email@gmail.com', TRUE, 12345, FALSE, 'STUDENT'),
    (7, 'a', 'Aa1!', 'a@', TRUE, 1234, FALSE, 'LABMANAGER');

INSERT INTO equipment (id, name, description, lab_location, status) VALUES
    (1, 'Microscope', 'Optical microscope for samples', 'Lab A', 'ENABLED'),
    (2, 'Centrifuge', 'Used to separate liquids', 'Lab B', 'MAINTENANCE'),
    (3, 'Spectrometer', 'Measures light spectra', 'Lab C', 'ENABLED'),
    (4, 'Pipette', 'Manual liquid handling', 'Lab A', 'DISABLED'),
    (5, 'Beaker Set', 'Glass beakers of various sizes', 'Lab B', 'ENABLED'),
    (6, 'nametest', 'desctest', 'locationtest', 'MAINTENANCE');

INSERT INTO reservations (id, start_time, end_time, equipment_id, user_id, status, total_owed, deposit_amount) VALUES
    (1, '2026-03-20 09:00:00', '2026-03-20 11:00:00', 1, 1, 'ACTIVE', 20, 10),
    (2, '2026-03-20 12:00:00', '2026-03-20 14:00:00', 2, 2, 'ACTIVE', 20, 10),
    (3, '2026-03-20 15:00:00', '2026-03-20 17:00:00', 3, 3, 'ACTIVE', 40, 20),
    (4, '2026-03-21 10:00:00', '2026-03-21 12:00:00', 1, 4, 'ACTIVE', 30, 15),
    (5, '2026-03-21 13:00:00', '2026-03-21 15:00:00', 2, 5, 'ACTIVE', 60, 30),
    (6, '2026-01-01 09:00:00', '2026-01-01 13:00:00', 1, 1, 'ACTIVE', 40, 10),
    (7, '2026-03-20 06:00:00', '2026-03-20 09:00:00', 1, 3, 'ACTIVE', 60, 20);
