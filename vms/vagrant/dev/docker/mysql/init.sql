CREATE DATABASE `aldebaran`;
CREATE USER 'aldebaran-admin'@'%' IDENTIFIED BY 'aldebaran-password';
GRANT USAGE ON `aldebaran`.* TO 'aldebaran-admin'@'%';
GRANT ALL PRIVILEGES ON `aldebaran`.* TO 'aldebaran-admin'@'%';

CREATE DATABASE `aldebaran-auth`;
CREATE USER 'aldebaran-auth-admin'@'%' IDENTIFIED BY 'aldebaran-auth-password';
GRANT USAGE ON `aldebaran-auth`.* TO 'aldebaran-auth-admin'@'%';
GRANT ALL PRIVILEGES ON `aldebaran-auth`.* TO 'aldebaran-auth-admin'@'%';