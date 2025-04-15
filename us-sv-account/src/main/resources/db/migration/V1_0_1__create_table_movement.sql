-- Tabla base: movement
CREATE TABLE movement
(
    movement_id VARCHAR(255) NOT NULL,
    account_id  VARCHAR(255) NOT NULL,
    type        VARCHAR(50)  NOT NULL,
    amount DOUBLE NOT NULL,
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (movement_id),
    CONSTRAINT fk_movement_account FOREIGN KEY (account_id) REFERENCES account (account_id)
);


