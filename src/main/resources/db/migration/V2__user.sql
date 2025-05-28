
CREATE TABLE users (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(100) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      role ENUM('ADMIN', 'USER') NOT NULL,
                      is_active BOOLEAN DEFAULT TRUE,
                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO users (username, password, role, is_active)
VALUES
    ('admin', '$2a$10$0.B13Sfty9nMwbRAcyKbNO3JvAKnVZ0dWMFRRqtbQd8Shsw7KAEly', 'ADMIN', TRUE),
    ('normal', '$2a$10$0.B13Sfty9nMwbRAcyKbNO3JvAKnVZ0dWMFRRqtbQd8Shsw7KAEly', 'USER', TRUE);
