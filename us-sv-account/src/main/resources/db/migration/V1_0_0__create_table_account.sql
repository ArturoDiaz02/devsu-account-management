-- Tabla base: account
CREATE TABLE account
(
    account_id      VARCHAR(255) NOT NULL PRIMARY KEY,
    document_number VARCHAR(255) NOT NULL,
    account_number  INT NOT NULL UNIQUE,
    type            VARCHAR(50) NOT NULL,
    status          VARCHAR(50) NOT NULL,
    balance         DOUBLE NOT NULL,
    opening_balance DOUBLE NOT NULL,
    created_at      DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Índices
CREATE INDEX idx_account_customer_id ON account (document_number);
CREATE INDEX idx_account_account_number ON account (account_number);
CREATE INDEX idx_account_customer_id_account_number ON account (document_number, account_number);

