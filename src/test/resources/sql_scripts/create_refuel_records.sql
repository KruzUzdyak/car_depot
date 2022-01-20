CREATE TABLE IF NOT EXISTS `refuel_records`
(
    `refuel_record_id` INT  NOT NULL AUTO_INCREMENT,
    `refuel_date`    BIGINT NOT NULL,
    `fuel_price`       INT  NOT NULL,
    `refuel_amount`    INT  NOT NULL,
    `car_id`           INT  NOT NULL,
    PRIMARY KEY (`refuel_record_id`),
    CONSTRAINT `car_id_refuel`
        FOREIGN KEY (`car_id`)
            REFERENCES `cars` (`car_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);