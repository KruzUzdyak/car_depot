-- -----------------------------------------------------
-- Schema car_depot
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `car_depot` DEFAULT CHARACTER SET utf8;
USE `car_depot`;

-- -----------------------------------------------------
-- Table `car_depot`.`license_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`license_types`;

CREATE TABLE IF NOT EXISTS `car_depot`.`license_types`
(
    `license_id`   INT         NOT NULL,
    `license_type` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`license_id`),
    UNIQUE INDEX `license_id_UNIQUE` (`license_id` ASC) VISIBLE
);

-- -----------------------------------------------------
-- Table `car_depot`.`car_types`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`car_types`;

CREATE TABLE IF NOT EXISTS `car_depot`.`car_types`
(
    `type_id`               INT          NOT NULL AUTO_INCREMENT,
    `car_type`              VARCHAR(100) NOT NULL,
    `load_type`             VARCHAR(100) NOT NULL,
    `required_license_type` INT          NOT NULL,
    PRIMARY KEY (`type_id`),
    UNIQUE INDEX `type_UNIQUE` (`car_type` ASC) VISIBLE,
    INDEX `license_type_idx` (`required_license_type` ASC) VISIBLE,
    CONSTRAINT `license_type_car_type`
        FOREIGN KEY (`required_license_type`)
            REFERENCES `car_depot`.`license_types` (`license_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `car_depot`.`car_models`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`car_models`;

CREATE TABLE IF NOT EXISTS `car_depot`.`car_models`
(
    `model_id`  INT          NOT NULL AUTO_INCREMENT,
    `car_model` VARCHAR(200) NOT NULL,
    `capacity`  INT          NOT NULL,
    `fuel_tank` INT          NOT NULL,
    `car_type`  INT          NOT NULL,
    PRIMARY KEY (`model_id`),
    INDEX `model_type_idx` (`car_type` ASC) VISIBLE,
    UNIQUE INDEX `model_UNIQUE` (`car_model` ASC) VISIBLE,
    CONSTRAINT `model_type_car_type`
        FOREIGN KEY (`car_type`)
            REFERENCES `car_depot`.`car_types` (`type_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `car_depot`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`roles`;

CREATE TABLE IF NOT EXISTS `car_depot`.`roles`
(
    `role_id` INT          NOT NULL AUTO_INCREMENT,
    `role`    VARCHAR(100) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE
);

-- -----------------------------------------------------
-- Table `car_depot`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`users`;

CREATE TABLE IF NOT EXISTS `car_depot`.`users`
(
    `user_id`  INT          NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `name`     VARCHAR(100) NOT NULL,
    `phone`    VARCHAR(100) NOT NULL,
    `role_id`  INT          NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
    INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `role_id_for_user`
        FOREIGN KEY (`role_id`)
            REFERENCES `car_depot`.`roles` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `car_depot`.`cars`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`cars`;

CREATE TABLE IF NOT EXISTS `car_depot`.`cars`
(
    `car_id`        INT         NOT NULL AUTO_INCREMENT,
    `plate_number`  VARCHAR(45) NOT NULL,
    `fuel_level`    INT         NOT NULL,
    `mileage`       INT         NOT NULL DEFAULT 10000,
    `broken`        BIT         NOT NULL DEFAULT 0,
    `car_model`     INT         NOT NULL,
    `driver_id`     INT         NULL,
    PRIMARY KEY (`car_id`),
    INDEX `car_model_idx` (`car_model` ASC) VISIBLE,
    UNIQUE INDEX `plate_number_UNIQUE` (`plate_number` ASC) VISIBLE,
    INDEX `drivers_id_idx` (`driver_id` ASC) VISIBLE,
    CONSTRAINT `car_model_cars`
        FOREIGN KEY (`car_model`)
            REFERENCES `car_depot`.`car_models` (`model_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `drivers_id_cars`
        FOREIGN KEY (`driver_id`)
            REFERENCES `car_depot`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `car_depot`.`driver_licenses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`driver_licenses`;

CREATE TABLE IF NOT EXISTS `car_depot`.`driver_licenses`
(
    `user_id`         INT         NOT NULL,
    `license_type`    INT         NOT NULL,
    `obtaining_date`  DATE        NOT NULL,
    `license_number` VARCHAR(45) NOT NULL,
    INDEX `license_type_idx` (`license_type` ASC) VISIBLE,
    CONSTRAINT `users_id_driver_licenses`
        FOREIGN KEY (`user_id`)
            REFERENCES `car_depot`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `license_type_for_driver`
        FOREIGN KEY (`license_type`)
            REFERENCES `car_depot`.`license_types` (`license_id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `car_depot`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`orders`;

CREATE TABLE IF NOT EXISTS `car_depot`.`orders`
(
    `order_id`    INT          NOT NULL AUTO_INCREMENT,
    `dest_from`   VARCHAR(200) NOT NULL,
    `dest_to`     VARCHAR(200) NOT NULL,
    `distance`    INT          NOT NULL,
    `date_start`  DATE         NOT NULL,
    `date_finish` DATE         NOT NULL,
    `load`        INT          NOT NULL,
    `load_note`   VARCHAR(200) NOT NULL,
    `completed`   BIT          NOT NULL DEFAULT 0,
    `payment`     INT          NOT NULL,
    `client_id`   INT          NOT NULL,
    `admin_id`    INT          NOT NULL,
    `cars_id`     INT          NULL,
    PRIMARY KEY (`order_id`),
    INDEX `clients_id_idx` (`client_id` ASC) VISIBLE,
    INDEX `admins_id_idx` (`admin_id` ASC) VISIBLE,
    INDEX `cars_id_idx` (`cars_id` ASC) VISIBLE,
    CONSTRAINT `clients_id_orders`
        FOREIGN KEY (`client_id`)
            REFERENCES `car_depot`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `admins_id_orders`
        FOREIGN KEY (`admin_id`)
            REFERENCES `car_depot`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `cars_id_orders`
        FOREIGN KEY (`cars_id`)
            REFERENCES `car_depot`.`cars` (`car_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `car_depot`.`repair_station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`repair_station`;

CREATE TABLE IF NOT EXISTS `car_depot`.`repair_station`
(
    `repair_record_id` INT  NOT NULL AUTO_INCREMENT,
    `repair_start`     DATE NOT NULL,
    `repair_end`       DATE NOT NULL,
    `expenses`         INT  NOT NULL,
    `car_id`           INT  NOT NULL,
    PRIMARY KEY (`repair_record_id`),
    CONSTRAINT `car_id_repair`
        FOREIGN KEY (`car_id`)
            REFERENCES `car_depot`.`cars` (`car_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `car_depot`.`client_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`client_info`;

CREATE TABLE IF NOT EXISTS `car_depot`.`client_info`
(
    `user_id` INT          NOT NULL,
    `company` VARCHAR(100) NOT NULL,
    `note`    TEXT         NULL,
    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
    PRIMARY KEY (`user_id`),
    CONSTRAINT `users_id_client_info`
        FOREIGN KEY (`user_id`)
            REFERENCES `car_depot`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);


-- -----------------------------------------------------
-- Table `car_depot`.`gas_station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `car_depot`.`gas_station`;

CREATE TABLE IF NOT EXISTS `car_depot`.`gas_station`
(
    `refuel_record_id` INT  NOT NULL AUTO_INCREMENT,
    `refuel_date`      DATE NOT NULL,
    `fuel_price`       INT  NOT NULL,
    `refuel_amount`    INT  NOT NULL,
    `car_id`           INT  NOT NULL,
    PRIMARY KEY (`refuel_record_id`),
    CONSTRAINT `car_id_refuel`
        FOREIGN KEY (`car_id`)
            REFERENCES `car_depot`.`cars` (`car_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);




