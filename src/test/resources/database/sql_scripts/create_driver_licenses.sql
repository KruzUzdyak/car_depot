CREATE TABLE IF NOT EXISTS `driver_licenses`
(
    `user_id`         INT         NOT NULL,
    `license_id`      INT         NOT NULL,
    `obtaining_date` BIGINT       NOT NULL,
    `license_number` VARCHAR(45)  NOT NULL,
    INDEX `license_type_idx` (`license_id` ASC) VISIBLE,
    PRIMARY KEY (`license_id`, `user_id`),
    CONSTRAINT `users_id_driver_licenses`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `license_type_for_driver`
        FOREIGN KEY (`license_id`)
            REFERENCES `license_types` (`license_id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);
