Create database Manufacture_Phone;

use manufacture_phone;

CREATE TABLE Manufacture (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    location VARCHAR(128),
    employee INT
);

CREATE TABLE Phone (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    price INT NOT NULL,
    color VARCHAR(128) NOT NULL,
    country VARCHAR(128),
    quantity INT
);

insert into manufacture(id, name, location, employee) values(1, "MF1", "HCM", 50);
insert into manufacture(id, name, location, employee) values(2, "MF2", "HN", 25);
insert into manufacture(id, name, location, employee) values(3, "MF3", "LD", 33);

select * from manufacture;

insert into phone(id, name, price, color, country, quantity) values(1, "Samsung", 950, "Blue", "Korea", 20);
insert into phone(id, name, price, color, country, quantity) values(2, "Iphone15", 2000, "Titanium", "US", 50);
insert into phone(id, name, price, color, country, quantity) values(3, "Oppo", 500, "White", "China", 35);

select * from phone