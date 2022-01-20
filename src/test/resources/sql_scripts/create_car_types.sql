CREATE TABLE IF NOT EXISTS `car_types`
(
    `type_id`               INT          NOT NULL AUTO_INCREMENT,
    `car_type`              VARCHAR(100) NOT NULL,
    `required_license_type` INT          NOT NULL,
    PRIMARY KEY (`type_id`),
    UNIQUE INDEX `type_UNIQUE` (`car_type` ASC) VISIBLE,
    INDEX `license_type_idx` (`required_license_type` ASC) VISIBLE,
    CONSTRAINT `license_type_car_type`
        FOREIGN KEY (`required_license_type`)
            REFERENCES `license_types` (`license_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);