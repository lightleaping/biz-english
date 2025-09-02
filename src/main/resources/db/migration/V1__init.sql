-- Flyway versioned migration: V1
CREATE TABLE IF NOT EXISTS flyway_check (
                                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                            note VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

INSERT INTO flyway_check(note) VALUES ('migration ok');
