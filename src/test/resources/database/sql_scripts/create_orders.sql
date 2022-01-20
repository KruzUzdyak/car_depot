CREATE TABLE IF NOT EXISTS `orders`
(
    `order_id`    INT          NOT NULL AUTO_INCREMENT,
    `dest_from`   VARCHAR(200) NOT NULL,
    `dest_to`     VARCHAR(200) NOT NULL,
    `distance`    INT          NOT NULL,
    `date_start`  BIGINT       NOT NULL,
    `date_end` BIGINT       NOT NULL,
    `load`        INT          NOT NULL,
    `load_note`   VARCHAR(200) NOT NULL,
    `completed`   BIT          NOT NULL DEFAULT 0,
    `payment`     INT          NOT NULL,
    `client_id`   INT          NOT NULL,
    `admin_id`    INT          NULL,
    `car_id`      INT          NULL,
    PRIMARY KEY (`order_id`),
    INDEX `clients_id_idx` (`client_id` ASC) VISIBLE,
    INDEX `admins_id_idx` (`admin_id` ASC) VISIBLE,
    INDEX `car_id_idx` (`car_id` ASC) VISIBLE,
    CONSTRAINT `clients_id_orders`
        FOREIGN KEY (`client_id`)
            REFERENCES `users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `admins_id_orders`
        FOREIGN KEY (`admin_id`)
            REFERENCES `users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `car_id_orders`
        FOREIGN KEY (`car_id`)
            REFERENCES `cars` (`car_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);