create database saludos;
use saludos;
create user 'yo'@'localhost' identified by '12345678';
create user 'yo'@'127.0.0.1' identified by '12345678';
grant all privileges on saludos.*to 'yo'@'localhost';
grant all privileges on saludos.*to 'yo'@'127.0.0.1'; 
flush privileges;