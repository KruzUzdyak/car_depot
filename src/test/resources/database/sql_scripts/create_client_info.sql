CREATE TABLE IF NOT EXISTS `client_info`
(
    `user_id` INT          NOT NULL,
    `company` VARCHAR(100) NULL,
    `note`    TEXT         NULL,
    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
    PRIMARY KEY (`user_id`),
    CONSTRAINT `users_id_client_info`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);