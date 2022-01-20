CREATE TABLE IF NOT EXISTS `cars`
(
    `car_id`        INT         NOT NULL AUTO_INCREMENT,
    `plate_number`  VARCHAR(45) NOT NULL,
    `fuel_level`    INT         NOT NULL,
    `mileage`       INT         NOT NULL DEFAULT 10000,
    `broken`        BIT         NOT NULL DEFAULT 0,
    `car_model_id`  INT         NOT NULL,
    `driver_id`     INT         NULL,
    PRIMARY KEY (`car_id`),
    INDEX `car_model_idx` (`car_model_id` ASC) VISIBLE,
    UNIQUE INDEX `plate_number_UNIQUE` (`plate_number` ASC) VISIBLE,
    INDEX `drivers_id_idx` (`driver_id` ASC) VISIBLE,
    CONSTRAINT `car_model_cars`
        FOREIGN KEY (`car_model_id`)
            REFERENCES `car_models` (`model_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `drivers_id_cars`
        FOREIGN KEY (`driver_id`)
            REFERENCES `users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);