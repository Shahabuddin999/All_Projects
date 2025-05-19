-- If another table, e.g., 'orders', has a foreign key to 'users'
ALTER TABLE IF EXISTS orders DROP CONSTRAINT IF EXISTS CONSTRAINT_8B;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE
);



DROP TABLE IF EXISTS orders;

CREATE TABLE orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product VARCHAR(100),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);



DROP TABLE IF EXISTS shahabuddin;

CREATE TABLE shahabuddin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    contact VARCHAR(100),
    address VARCHAR(255),
    mobile VARCHAR(20)
);
