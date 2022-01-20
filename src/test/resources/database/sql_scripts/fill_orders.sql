INSERT INTO `orders` (`order_id`, `dest_from`, `dest_to`, `distance`, `date_start`, `date_end`,
                                  `load`, `load_note`, `completed`, `payment`, `client_id`, `admin_id`, `car_id`)
VALUES (1, 'Uzda', 'Minsk', 70, 1638359946000, 1641359946000, 400, 'Packaget milk', 0, 1000, 3, 1, NULL),
       (2, 'Village', 'Uzda', 10, 1637359946000, 1640359946000, 3000, 'Milk in tank', 0, 1500, 3, 1, NULL),
       (3, 'Town', 'City', 130, 1637354446000, 1640359646000, 3000, 'Paper', 1, 10500, 4, 2, 3),
       (4, 'City', 'Village', 150, 1637354546000, 1640389646000, 320, 'Workers', 1, 1500, 4, 2, 1);
