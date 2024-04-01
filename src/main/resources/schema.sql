CREATE TABLE IF NOT EXISTS country(
    countryId int PRIMARY KEY AUTO_INCREMENT,
    countryName varchar(255),
    currency varchar(255),
    population int,
    latitude varchar(255),
    longitude varchar(255)
);

CREATE TABLE IF NOT EXISTS city (
    cityId int PRIMARY KEY AUTO_INCREMENT,
    cityName varchar(255),
    population int,
    latitude varchar(255),
    longitude varchar(255),
    countryId int ,

    FOREIGN KEY (countryId) REFERENCES country(countryId)
);