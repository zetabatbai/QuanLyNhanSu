CREATE DATABASE IF NOT EXISTS `qlnsHT`;

USE `qlnsHT`;


CREATE TABLE `account`
(
    `username` VARCHAR(255) PRIMARY KEY,
    `password` VARCHAR(255) NOT NULL
);
INSERT INTO `account`(username, password) VALUES ('admin', '123');


CREATE TABLE `employee`
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullName VARCHAR(255),
    address VARCHAR(555),
    phone VARCHAR(20),
    gender VARCHAR(20),
    position VARCHAR(100),
    department VARCHAR(150),
    isDeleted TINYINT(2) DEFAULT 0
);

CREATE TABLE contract
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  employeeId INT,
  contractType VARCHAR(255),
  startDate VARCHAR(255),
  endDate VARCHAR(255),
  isDeleted TINYINT(2) DEFAULT 0
);

CREATE TABLE recuitment
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  position VARCHAR(255),
  quantity INT,
  deadline VARCHAR(255),
  jobDescription VARCHAR(555)
);

CREATE TABLE salary
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    employee_id    INT,
    salary_amount FLOAT,
    salary_date   VARCHAR(255),
    detail        VARCHAR(255),
    CONSTRAINT fk_salary_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);


ALTER TABLE contract ADD FOREIGN KEY (employeeId) REFERENCES employee(id);