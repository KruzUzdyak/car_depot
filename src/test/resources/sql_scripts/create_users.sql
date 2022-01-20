CREATE TABLE IF NOT EXISTS `users`
(
    `user_id`  INT          NOT NULL AUTO_INCREMENT,
    `login`    VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `name`     VARCHAR(100) NOT NULL,
    `phone`    VARCHAR(100) NOT NULL,
    `role_id`  INT          NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `role_id_for_user`
        FOREIGN KEY (`role_id`)
            REFERENCES `roles` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);