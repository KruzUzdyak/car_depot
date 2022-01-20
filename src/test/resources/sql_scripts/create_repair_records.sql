CREATE TABLE IF NOT EXISTS `repair_records`
(
    `repair_record_id` INT  NOT NULL AUTO_INCREMENT,
    `repair_start`   BIGINT NOT NULL,
    `repair_end`     BIGINT NOT NULL,
    `expenses`         INT  NOT NULL,
    `car_id`           INT  NOT NULL,
    PRIMARY KEY (`repair_record_id`),
    CONSTRAINT `car_id_repair`
        FOREIGN KEY (`car_id`)
            REFERENCES `cars` (`car_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);