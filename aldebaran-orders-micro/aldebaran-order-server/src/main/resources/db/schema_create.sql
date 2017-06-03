CREATE DATABASE omanager;
CREATE USER 'omanager'@'localhost' IDENTIFIED BY '123456789';
GRANT USAGE ON *.* TO 'omanager'@'localhost';
GRANT ALL PRIVILEGES ON omanager.* TO 'omanager'@'localhost';


CREATE USER 'omanager'@'%' IDENTIFIED BY '123456789';
GRANT USAGE ON *.* TO 'omanager'@'%';
GRANT ALL PRIVILEGES ON omanager.* TO 'omanager'@'%';
