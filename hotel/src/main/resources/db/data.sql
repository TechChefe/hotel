INSERT INTO customers (first_name, last_name, email, phone, address) VALUES
('Γιώργος',  'Παπαδόπουλος', 'gpapad@email.com',  '6901234567', 'Αθήνα'),
('Μαρία',    'Νικολάου',     'mnikolao@email.com', '6912345678', 'Θεσσαλονίκη'),
('Κώστας',   'Αντωνίου',     'kantonio@email.com', '6923456789', 'Πάτρα');

INSERT INTO rooms (room_number, type, price_per_night, capacity, description) VALUES
(101, 'SINGLE', 60.00,  1, 'Μονόκλινο με θέα κήπο'),
(102, 'DOUBLE', 90.00,  2, 'Δίκλινο με θέα θάλασσα'),
(201, 'SUITE',  200.00, 2, 'Σουίτα με τζακούζι'),
(202, 'FAMILY', 150.00, 4, 'Οικογενειακό δωμάτιο');

INSERT INTO bookings (customer_id, room_id, check_in, check_out, status, total_cost) VALUES
(1, 1, '2025-06-01', '2025-06-05', 'CONFIRMED', 240.00),
(2, 2, '2025-06-10', '2025-06-15', 'CONFIRMED', 450.00),
(3, 3, '2025-07-01', '2025-07-07', 'PENDING',   1400.00);