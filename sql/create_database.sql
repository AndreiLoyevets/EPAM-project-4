CREATE DATABASE IF NOT EXISTS `railway_system` CHARACTER SET UTF8;

CREATE TABLE `railway_system`.`users` (
  `id` INT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(35) NOT NULL,
  `firstName` VARCHAR(35) NOT NULL,
  `surname` VARCHAR(35) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));


#insert into users (email, password, firstName, surname, phone) values ("a@gmail.com", "111", "Frank", "Jones", "333");

# record example: 143K Київ - Івано-Франківськ 21:00 - 09:37, Л(suite) 20, К(coupe) 40, П(berth) 54
CREATE TABLE `railway_system`.`routes` (
  `id` VARCHAR(4) NOT NULL,
  `departureStation` VARCHAR(70) NOT NULL,
  `destinationStation` VARCHAR(70) NOT NULL,
  `departureTime` TIME NOT NULL,
  `destinationTime` TIME NOT NULL,
  `suitePlaces` INT NOT NULL,
  `coupePlaces` INT NOT NULL,
  `berthPlaces` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `departure` (`departureStation` ASC),
  INDEX `destination` (`destinationStation` ASC));

CREATE TABLE `railway_system`.`trains` (
  `id` INT NULL AUTO_INCREMENT,
  `routeID` VARCHAR(4) NOT NULL,
  `date` DATE NOT NULL,
  `suiteReserved` INT NOT NULL,
  `coupeReserved` INT NOT NULL,
  `berthReserved` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `train_route_idx` (`routeID` ASC),
  CONSTRAINT `train_route`
    FOREIGN KEY (`routeID`)
    REFERENCES `railway_system`.`routes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `railway_system`.`prices` (
  `id` INT NULL AUTO_INCREMENT,
  `routeID` VARCHAR(4) NOT NULL,
  `suitePrice` INT NOT NULL,
  `routePrice` INT NOT NULL,
  `berthPrice` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `price_route_idx` (`routeID` ASC),
  CONSTRAINT `price_route`
    FOREIGN KEY (`routeID`)
    REFERENCES `railway_system`.`routes` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `railway_system`.`invoices` (
  `id` INT NULL AUTO_INCREMENT,
  `passengerID` INT NOT NULL,
  `trainID` INT NOT NULL,
  `placeType` ENUM('suite', 'coupe', 'berth') NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `passenger_idx` (`passengerID` ASC),
  INDEX `invoice_train_idx` (`trainID` ASC),
  CONSTRAINT `passenger`
    FOREIGN KEY (`passengerID`)
    REFERENCES `railway_system`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `invoice_train`
    FOREIGN KEY (`trainID`)
    REFERENCES `railway_system`.`trains` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
COMMENT = 'Price is included because we need the price for the moment of
making reservations. The actual price for tickets can vary from time to time.';
