CREATE TABLE IF NOT EXISTS `license_types`
(
    `license_id`   INT         NOT NULL,
    `license_type` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`license_id`),
    UNIQUE INDEX `license_id_UNIQUE` (`license_id` ASC) VISIBLE
);