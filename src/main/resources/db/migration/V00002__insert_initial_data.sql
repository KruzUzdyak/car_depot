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
INSERT INTO `car_depot`.`car_models` (`model_id`, `car_model`, `capacity`, `load_type`, `fuel_tank`, `car_type_id`)
VALUES (1, 'Volkswagen Transporter T4', 8, 'people', 70, 1);
INSERT INTO `car_depot`.`car_models` (`model_id`, `car_model`, `capacity`, `load_type`, `fuel_tank`, `car_type_id`)
VALUES (2, 'MAZ 232', 35, 'people', 300, 2);
INSERT INTO `car_depot`.`car_models` (`model_id`, `car_model`, `capacity`, `load_type`, `fuel_tank`, `car_type_id`)
VALUES (3, 'Scania P', 1000, 'goods', 150, 3);
INSERT INTO `car_depot`.`car_models` (`model_id`, `car_model`, `capacity`, `load_type`, `fuel_tank`, `car_type_id`)
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
VALUES (1, 'admin1', '$2a$10$IqRkpIcXhakP1tj1oGLxyuPECEaxKOpN1RsUqLhVcAQ8W02.i2hPG', 'Strelkov Viktor', '+375297766858', 1);

INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (2, 'admin2', '$2a$10$HtuoSIiPCDGF4gQF44TDsO8pq0M2ZlugjySz9fP/ehvVNDy884OUm', 'Starkin Andrey', '+37529774544', 1);

INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (3, 'client1', '$2a$10$uIzLUf2wlG3cZzlHge/KyuBKhB5nSdyDkFjOUxEbEbELub9kIwFSK', 'Zuzkin Gennadiy', '+375356794315', 3);

INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (4, 'client2', '$2a$10$eeIc6X24Fr6iQ81M2P3GKecHZSVYvOLHR8GLapYCvTEVJL9uqLVNG', 'Kertov Grigoriy', '+375356634315', 3);

INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (5, 'driver1', '$2a$10$Bb47ignhQuruq7nkFsVQCuqCxZJvoj3Cq29G3BOGb4HhKWbgJDak6', 'Gruzdev Anatoly', '+375336462214', 2);

INSERT INTO `car_depot`.`users` (`user_id`, `login`, `password`, `name`, `phone`, `role_id`)
VALUES (6, 'driver2', '$2a$10$d591kb2xhd0V6EzhAWiJfOxfImbs/BjW3gjZPB/bJd7DVaKhg4hT.', 'Samsonov Semen',  '+375354789476', 2);

-- -----------------------------------------------------
-- Data for table `car_depot`.`cars`
-- -----------------------------------------------------

INSERT INTO `car_depot`.`cars` (`car_id`, `plate_number`, `fuel_level`, `mileage`, `broken`, `car_model_id`,
                                `driver_id`)
VALUES (1, '6795MM-5', 50, 12000, 1, 1, NULL);
INSERT INTO `car_depot`.`cars` (`car_id`, `plate_number`, `fuel_level`, `mileage`, `broken`, `car_model_id`,
                                `driver_id`)
VALUES (2, '6949BM-5', 200, 25000, 1, 2, NULL);
INSERT INTO `car_depot`.`cars` (`car_id`, `plate_number`, `fuel_level`, `mileage`, `broken`, `car_model_id`,
                                `driver_id`)
VALUES (3, '1231BC-5', 121, 20000, 0, 3, 5);
INSERT INTO `car_depot`.`cars` (`car_id`, `plate_number`, `fuel_level`, `mileage`, `broken`, `car_model_id`,
                                `driver_id`)
VALUES (4, '8301KO-5', 400, 30000, 0, 4, 6);


-- -----------------------------------------------------
-- Data for table `car_depot`.`driver_licenses`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`driver_licenses` (`user_id`, `license_id`, `obtaining_date`, `license_number`)
VALUES (5, 5, 1614190346000, '7AB250666');
INSERT INTO `car_depot`.`driver_licenses` (`user_id`, `license_id`, `obtaining_date`, `license_number`)
VALUES (6, 5, 1621190346000, '6BC654899');
INSERT INTO `car_depot`.`driver_licenses` (`user_id`, `license_id`, `obtaining_date`, `license_number`)
VALUES (6, 8, 1623190346000, '6MM645211');
-- -----------------------------------------------------
-- Data for table `car_depot`.`orders`
-- -----------------------------------------------------

INSERT INTO `car_depot`.`orders` (`order_id`, `dest_from`, `dest_to`, `distance`, `date_start`, `date_end`,
                                  `load`, `load_note`, `completed`, `payment`, `client_id`, `admin_id`, `car_id`)
VALUES (1, 'Uzda', 'Minsk', 70, 1638359946000, 1641359946000, 400, 'Packaget milk', 0, 1000, 3, 1, NULL);
INSERT INTO `car_depot`.`orders` (`order_id`, `dest_from`, `dest_to`, `distance`, `date_start`, `date_end`,
                                  `load`, `load_note`, `completed`, `payment`, `client_id`, `admin_id`, `car_id`)
 VALUES (2, 'Village', 'Uzda', 10, 1637359946000, 1640359946000, 3000, 'Milk in tank', 0, 1500, 3, 1, NULL);
INSERT INTO `car_depot`.`orders` (`order_id`, `dest_from`, `dest_to`, `distance`, `date_start`, `date_end`,
                                  `load`, `load_note`, `completed`, `payment`, `client_id`, `admin_id`, `car_id`)
VALUES (3, 'Town', 'City', 130, 1637354446000, 1640359646000, 3000, 'Paper', 1, 10500, 4, 2, 3);
INSERT INTO `car_depot`.`orders` (`order_id`, `dest_from`, `dest_to`, `distance`, `date_start`, `date_end`,
                                  `load`, `load_note`, `completed`, `payment`, `client_id`, `admin_id`, `car_id`)
VALUES (4, 'City', 'Village', 150, 1637354546000, 1640389646000, 320, 'Workers', 1, 1500, 4, 2, 1);

-- -----------------------------------------------------
-- Data for table `car_depot`.`repair_records`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`repair_records` (`repair_record_id`, `repair_start`, `repair_end`, `expenses`, `car_id`)
VALUES (1, 1638359946000, 1639359946000, 10000, 1);

INSERT INTO `car_depot`.`repair_records` (`repair_record_id`, `repair_start`, `repair_end`, `expenses`, `car_id`)
VALUES (2, 1637359946000, 1639359946000, 30000, 2);

INSERT INTO `car_depot`.`repair_records` (`repair_record_id`, `repair_start`, `repair_end`, `expenses`, `car_id`)
VALUES (3, 163735446000, 163935646000, 14000, 3);

INSERT INTO `car_depot`.`repair_records` (`repair_record_id`, `repair_start`, `repair_end`, `expenses`, `car_id`)
VALUES (4, 163735746000, 163935946000, 11100, 4);

-- -----------------------------------------------------
-- Data for table `car_depot`.`client_info`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`client_info` (`user_id`, `company`, `note`)
VALUES (3, 'LTD Co Inc', 'Nice guy');
INSERT INTO `car_depot`.`client_info` (`user_id`, `company`, `note`)
VALUES (4, 'JST Infrared', 'China');

-- -----------------------------------------------------
-- Data for table `car_depot`.`admin_info`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`admin_info` (`user_id`, `works_since`, `note`)
VALUES (1, 1610279946000, 'Best worker');
INSERT INTO `car_depot`.`admin_info` (`user_id`, `works_since`, `note`)
VALUES (2, 1615279946000, 'New worker');

-- -----------------------------------------------------
-- Data for table `car_depot`.`refuel_records`
-- -----------------------------------------------------
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (1, 1624190346000, 1.8, 30, 1);
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (2, 1624290346000, 2.1, 50, 2);
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (3, 1624390346000, 2.1, 40, 3);
INSERT INTO `car_depot`.`refuel_records` (`refuel_record_id`, `refuel_date`, `fuel_price`, `refuel_amount`, `car_id`)
VALUES (4, 1624490346000, 2, 200, 4);


