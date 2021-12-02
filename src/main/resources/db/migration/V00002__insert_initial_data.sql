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
VALUES (1, 'administrator');
INSERT INTO `car_depot`.`roles` (`role_id`, `role`)
VALUES (2, 'driver');
INSERT INTO `car_depot`.`roles` (`role_id`, `role`)
VALUES (3, 'client');

-- -----------------------------------------------------
-- Data for table `car_depot`.`users`
-- -----------------------------------------------------

INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (1, 'admin', 'admin', 'Strelkov Viktor', '+375297766858', 1);
INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (2, 'driver1', 'driver1', 'Gruzdev Anatoly', '+375336462214', 2);
INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (3, 'driver2', 'driver2', 'Samsonov Semen',  '+375354789476', 2);
INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (4, 'client', 'client', 'Zuzkin Gennadiy', '+375356794315', 3);

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
VALUES (2, 5, '2011.11.11', '7AB250666');
INSERT INTO `car_depot`.`driver_licenses` (`user_id`, `license_id`, `obtaining_date`, `license_number`)
VALUES (3, 5, '2012.12.12', '6BC654899');
INSERT INTO `car_depot`.`driver_licenses` (`user_id`, `license_id`, `obtaining_date`, `license_number`)
VALUES (3, 8, '2017.12.17', '6MM645211');

-- -----------------------------------------------------
-- Data for table `car_depot`.`orders`
-- -----------------------------------------------------

INSERT INTO `car_depot`.`orders` (`order_id`, `dest_from`, `dest_to`, `distance`, `date_start`, `date_finish`, `load`,
                                  `load_note`, `completed`, `payment`, `client_id`, `admin_id`, `car_id`)
VALUES (1, 'Uzda', 'Minsk', 70, '2021.12.20', '2021.12.20', 400, 'Packaged milk', 0, 1000, 4, 1, NULL);
INSERT INTO `car_depot`.`orders` (`order_id`, `dest_from`, `dest_to`, `distance`, `date_start`, `date_finish`, `load`,
                                  `load_note`, `completed`, `payment`, `client_id`, `admin_id`, `car_id`)
VALUES (2, 'Farm', 'Uzda', 10, '2021.10.18', '2021.12.18', 3000, 'Milk in tank', 0, 1500, 4, 1, NULL);

-- -----------------------------------------------------
-- Data for table `car_depot`.`repair_records`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`repair_records` (`repair_record_id`, `repair_start`, `repair_end`, `expenses`, `car_id`)
VALUES (1, '2021.10.10', '2022.02.20', 10000, 1);
INSERT INTO `car_depot`.`repair_records` (`repair_record_id`, `repair_start`, `repair_end`, `expenses`, `car_id`)
VALUES (2, '2021.11.11', '2022.02.20', 30000, 2);

-- -----------------------------------------------------
-- Data for table `car_depot`.`client_info`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`client_info` (`user_id`, `company`, `note`)
VALUES (4, 'LTD Co Inc', 'Nice guy');

-- -----------------------------------------------------
-- Data for table `car_depot`.`refuel_records`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (1, '2021.10.02', 1.8, 30, 1);
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (2, '2021.10.01', 2.1, 50, 2);
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (3, '2021.10.10', 2.1, 40, 3);
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (4, '2021.09.23', 2, 200, 4);


