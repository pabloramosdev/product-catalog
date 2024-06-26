CREATE TABLE IF NOT EXISTS PRICE (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    BRAND_ID INT NOT NULL,
    PRODUCT_ID INT NOT NULL,
    START_DATE TIMESTAMP NOT NULL,
    END_DATE TIMESTAMP,
    PRICE_LIST INT NOT NULL,
    PRIORITY INT NOT NULL,
    PRICE DECIMAL(4,2) NOT NULL,
    CURRENCY CHAR(3) NOT NULL
);