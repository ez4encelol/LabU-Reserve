CREATE INDEX idx_reservations_user ON reservations(user_id);
CREATE INDEX idx_reservations_equipment ON reservations(equipment_id);
CREATE INDEX idx_reservations_status ON reservations(status);
CREATE INDEX idx_users_email ON users(email);
