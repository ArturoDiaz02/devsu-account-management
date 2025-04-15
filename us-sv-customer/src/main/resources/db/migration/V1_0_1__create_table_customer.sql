-- Tabla hija: CUSTOMERS (hereda de users)
CREATE TABLE customer
(
    user_id             VARCHAR(255) NOT NULL PRIMARY KEY,
    password            VARCHAR(255) NOT NULL,
    status              VARCHAR(50)  NOT NULL,
    customer_created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    customer_updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_customer_user FOREIGN KEY (user_id) REFERENCES user (user_id)
);
