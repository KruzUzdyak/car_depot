CREATE TABLE IF NOT EXISTS `admin_info`
(
    `user_id`     INT    NOT NULL,
    `works_since` BIGINT NULL,
    `note`        TEXT   NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
    CONSTRAINT `admin_info_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);