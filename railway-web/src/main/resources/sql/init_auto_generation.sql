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
VALUES ('6', '1', 'train_number', 'IND/RAIL/TRN/', '2024/');

INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('7', '1', 'user_number', 'IND/RAIL/USR/', '2024/');

INSERT IGNORE INTO `auto_code_generation` (`id`, `current_value`, `entity_name`, `prefix`, `year`)
VALUES ('8', '1', 'user_numberR', 'IND/RAIL/USER/', '2024/');

INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('1', 'kk', 'IND/RAIL/TRN/2024/00006');
INSERT IGNORE INTO `train` (`id`, `train_name`, `train_number`) VALUES ('2', 'k2', 'IND/RAIL/TRN/2024/00007');

INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00002', 'password123', 'IND/RAIL/USR/2024/00002', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00003', 'password123', 'IND/RAIL/USR/2024/00003', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00004', 'password123', 'IND/RAIL/USR/2024/00004', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00005', 'password123', 'IND/RAIL/USR/2024/00004', 'akashuser');
INSERT IGNORE INTO `users` (`id`, `password`, `user_number`, `username`) VALUES ('00006', 'password123', 'IND/RAIL/USR/2024/00004', 'akashuser');
