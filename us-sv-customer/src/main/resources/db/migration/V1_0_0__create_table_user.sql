-- Tabla base: USERS
CREATE TABLE user
(
    user_id         VARCHAR(255) NOT NULL PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    gender          VARCHAR(20)  NOT NULL,
    age             INT          NOT NULL CHECK (age >= 0),
    document_number VARCHAR(100) NOT NULL UNIQUE,
    address         VARCHAR(255),
    phone           VARCHAR(50)  NOT NULL UNIQUE,
    user_created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Índice para facilitar búsqueda por document_number (usado por Customer)
CREATE INDEX idx_users_document_number ON user (document_number);
