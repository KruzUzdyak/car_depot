-- -----------------------------------------------------
-- Data for table `car_depot`.`license_types`
-- -----------------------------------------------------

INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (1, 'AM');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (2, 'A');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (3, 'A1');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (4, 'B');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (5, 'C');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (6, 'D');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (7, 'BE');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (8, 'CE');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (9, 'DE');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (10, 'F');
INSERT INTO `car_depot`.`license_types` (`license_id`, `license_type`)
VALUES (11, 'I');

-- -----------------------------------------------------
-- Data for table `car_depot`.`car_types`
-- -----------------------------------------------------

INSERT INTO `car_depot`.`car_types` (`type_id`, `car_type`, `required_license_type`)
VALUES (1, 'small bus', 4);
INSERT INTO `car_depot`.`car_types` (`type_id`, `car_type`, `required_license_type`)
VALUES (2, 'bus', 6);
INSERT INTO `car_depot`.`car_types` (`type_id`, `car_type`, `required_license_type`)
VALUES (3, 'small truck', 5);
INSERT INTO `car_depot`.`car_types` (`type_id`, `car_type`, `required_license_type`)
VALUES (4, 'big truck with trailer', 8);

-- -----------------------------------------------------
-- Data for table `car_depot`.`car_models`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`car_models` (`model_id`, `car_model`, `capacity`, `load_type`, `fuel_tank`, `car_type`)
VALUES (1, 'Volkswagen Transporter T4', 8, 'people', 70, 1);
INSERT INTO `car_depot`.`car_models` (`model_id`, `car_model`, `capacity`, `load_type`, `fuel_tank`, `car_type`)
VALUES (2, 'MAZ 232', 35, 'people', 300, 2);
INSERT INTO `car_depot`.`car_models` (`model_id`, `car_model`, `capacity`, `load_type`, `fuel_tank`, `car_type`)
VALUES (3, 'Scania P', 1000, 'goods', 150, 3);
INSERT INTO `car_depot`.`car_models` (`model_id`, `car_model`, `capacity`, `load_type`, `fuel_tank`, `car_type`)
VALUES (4, 'Scania S', 5000, 'goods', 600, 4);

-- -----------------------------------------------------
-- Data for table `car_depot`.`roles`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`roles` (`role_id`, `role`)
VALUES (1, 'admin');
INSERT INTO `car_depot`.`roles` (`role_id`, `role`)
VALUES (2, 'driver');
INSERT INTO `car_depot`.`roles` (`role_id`, `role`)
VALUES (3, 'client');

-- -----------------------------------------------------
-- Data for table `car_depot`.`users`
-- -----------------------------------------------------

INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (1, 'admin1', 'admin1', 'Strelkov Viktor', '+375297766858', 1);
INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (2, 'driver1', 'driver1', 'Gruzdev Anatoly', '+375336462214', 2);
INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (3, 'driver2', 'driver2', 'Samsonov Semen',  '+375354789476', 2);
INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (4, 'client1', 'client1', 'Zuzkin Gennadiy', '+375356794315', 3);
INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (5, 'admin2', 'admin2', 'Starkin Andrey', '+37529774544', 1);
INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (6, 'client2', 'client2', 'Kertov Grigoriy', '+375356634315', 3);

-- -----------------------------------------------------
-- Data for table `car_depot`.`cars`
-- -----------------------------------------------------

INSERT INTO `car_depot`.`cars` (`car_id`, `plate_number`, `fuel_level`, `mileage`, `broken`, `car_model`,
                                `driver_id`)
VALUES (1, '6795MM-5', 50, 12000, 1, 1, NULL);
INSERT INTO `car_depot`.`cars` (`car_id`, `plate_number`, `fuel_level`, `mileage`, `broken`, `car_model`,
                                `driver_id`)
VALUES (2, '6949BM-5', 200, 25000, 1, 2, NULL);
INSERT INTO `car_depot`.`cars` (`car_id`, `plate_number`, `fuel_level`, `mileage`, `broken`, `car_model`,
                                `driver_id`)
VALUES (3, '1231BC-5', 121, 20000, 0, 3, 2);
INSERT INTO `car_depot`.`cars` (`car_id`, `plate_number`, `fuel_level`, `mileage`, `broken`, `car_model`,
                                `driver_id`)
VALUES (4, '8301KO-5', 400, 30000, 0, 4, 3);


-- -----------------------------------------------------
-- Data for table `car_depot`.`driver_licenses`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`driver_licenses` (`user_id`, `license_id`, `obtaining_date`, `license_number`)
VALUES (2, 5, 1614190346, '7AB250666');
INSERT INTO `car_depot`.`driver_licenses` (`user_id`, `license_id`, `obtaining_date`, `license_number`)
VALUES (3, 5, 1621190346, '6BC654899');
INSERT INTO `car_depot`.`driver_licenses` (`user_id`, `license_id`, `obtaining_date`, `license_number`)
VALUES (3, 8, 1623190346, '6MM645211');
-- -----------------------------------------------------
-- Data for table `car_depot`.`orders`
-- -----------------------------------------------------

INSERT INTO `car_depot`.`orders` (`order_id`, `dest_from`, `dest_to`, `distance`, `date_start`, `date_end`,
`load`, `load_note`, `completed`, `payment`, `client_id`, `admin_id`, `car_id`)
VALUES (1, 'Uzda', 'Minsk', 70, 1638359946, 1641359946, 400, 'Packaget milk', 0, 1000, 4, 1, NULL);
INSERT INTO `car_depot`.`orders` (`order_id`, `dest_from`, `dest_to`, `distance`, `date_start`, `date_end`,
`load`, `load_note`, `completed`, `payment`, `client_id`, `admin_id`, `car_id`)
 VALUES (2, 'Village', 'Uzda', 10, 1637359946, 1640359946, 3000, 'Milk in tank', 0, 1500, 4, 1, NULL);


-- -----------------------------------------------------
-- Data for table `car_depot`.`repair_records`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`repair_records` (`repair_record_id`, `repair_start`, `repair_end`, `expenses`, `car_id`)
VALUES (1, 1638359946, 1639359946, 10000, 1);
INSERT INTO `car_depot`.`repair_records` (`repair_record_id`, `repair_start`, `repair_end`, `expenses`, `car_id`)
VALUES (2, 1637359946, 1639359946, 30000, 2);

-- -----------------------------------------------------
-- Data for table `car_depot`.`client_info`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`client_info` (`user_id`, `company`, `note`)
VALUES (4, 'LTD Co Inc', 'Nice guy');
INSERT INTO `car_depot`.`client_info` (`user_id`, `company`, `note`)
VALUES (6, 'JST Infrared', 'China');

-- -----------------------------------------------------
-- Data for table `car_depot`.`admin_info`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`admin_info` (`user_id`, `works_since`, `note`)
VALUES (1, 1610279946, 'Best worker');
INSERT INTO `car_depot`.`admin_info` (`user_id`, `works_since`, `note`)
VALUES (5, 1615279946, 'New worker');

-- -----------------------------------------------------
-- Data for table `car_depot`.`refuel_records`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (1, 1624190346, 1.8, 30, 1);
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (2, 1624290346, 2.1, 50, 2);
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (3, 1624390346, 2.1, 40, 3);
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (4, 1624490346, 2, 200, 4);


