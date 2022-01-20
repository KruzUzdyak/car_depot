CREATE TABLE IF NOT EXISTS `roles`
(
    `role_id` INT          NOT NULL AUTO_INCREMENT,
    `role`    VARCHAR(100) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE INDEX `role_UNIQUE` (`role` ASC) VISIBLE
);