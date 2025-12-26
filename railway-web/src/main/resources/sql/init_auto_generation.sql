INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('1', '1', 'passenger_entity', '', '');

INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('2', '1', 'address_entity', '', '');

INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('3', '1', 'user_entity', '', '');

INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('4', '1', 'train_entity', '', '');

INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('5', '1', 'passenger_pnr_number', 'IND/RAIL/PNR/', '2024/');

INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('6', '9', 'train_number', 'IND/RAIL/TRN/', '2024/');

INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('7', '11', 'user_number', 'IND/RAIL/USR/', '2024/');

INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('8', '1', 'user_numberR', 'IND/RAIL/USER/', '2024/');

INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('1', 'kk', 'IND/RAIL/TRN/2024/00001');
INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('2', 'k2', 'IND/RAIL/TRN/2024/00002');
INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('3', 'kk', 'IND/RAIL/TRN/2024/00003');
INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('4', 'k2', 'IND/RAIL/TRN/2024/00004');
INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('5', 'kk', 'IND/RAIL/TRN/2024/00005');
INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('6', 'k2', 'IND/RAIL/TRN/2024/00006');
INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('7', 'kk', 'IND/RAIL/TRN/2024/00007');
INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('8', 'k2', 'IND/RAIL/TRN/2024/00008');

INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00001', 'password123', 'IND/RAIL/USR/2024/00001', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00002', 'password123', 'IND/RAIL/USR/2024/00002', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00003', 'password123', 'IND/RAIL/USR/2024/00003', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00004', 'password123', 'IND/RAIL/USR/2024/00004', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00005', 'password123', 'IND/RAIL/USR/2024/00005', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00006', 'password123', 'IND/RAIL/USR/2024/00006', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00007', 'password123', 'IND/RAIL/USR/2024/00007', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00008', 'password123', 'IND/RAIL/USR/2024/00008', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00009', 'password123', 'IND/RAIL/USR/2024/00009', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00010', 'password123', 'IND/RAIL/USR/2024/00010', 'akashuser');

INSERT IGNORE INTO `employee` (`id`, `address`, `dateofbirth`, `gender`, `name`, `qualification`) VALUES ('1', 'Bangalore', '1998-05-10', 'Male', 'Akash', 'B.Tech');
INSERT IGNORE INTO `employee` (`id`, `address`, `dateofbirth`, `gender`, `name`, `qualification`, `manager_id`) VALUES ('2', 'Bangalore', '1998-05-10', 'Male', 'Bhanu', 'B.Tech', '1');
INSERT IGNORE INTO `employee` (`id`, `address`, `dateofbirth`, `gender`, `name`, `qualification`, `manager_id`) VALUES ('3', 'Chennai', '1985-03-15', 'Male', 'Chandra', 'M.Tech', '1');
INSERT IGNORE INTO `employee` (`id`, `address`, `dateofbirth`, `gender`, `name`, `qualification`, `manager_id`) VALUES ('4', 'Chennai', '1999-01-10', 'Male', 'Daniel', 'B.Sc', '2');
INSERT IGNORE INTO `employee` (`id`, `address`, `dateofbirth`, `gender`, `name`, `qualification`, `manager_id`) VALUES ('5', 'Chennai', '2000-07-22', 'Female', 'Eshawar', 'BCA', '4');
