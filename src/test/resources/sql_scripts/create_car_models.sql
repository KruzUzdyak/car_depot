CREATE TABLE IF NOT EXISTS `car_models`
(
    `model_id`  INT          NOT NULL AUTO_INCREMENT,
    `car_model` VARCHAR(200) NOT NULL,
    `capacity`  INT          NOT NULL,
    `load_type` VARCHAR(100) NOT NULL,
    `fuel_tank` INT          NOT NULL,
    `car_type_id`  INT          NOT NULL,
    PRIMARY KEY (`model_id`),
    INDEX `model_type_idx` (`car_type_id` ASC) VISIBLE,
    UNIQUE INDEX `model_UNIQUE` (`car_model` ASC) VISIBLE,
    CONSTRAINT `model_type_car_type`
        FOREIGN KEY (`car_type_id`)
            REFERENCES `car_types` (`type_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);