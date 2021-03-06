insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Stefan', 'Pejakovic', 'spejakovic5@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'XVI Divizija 137', 'Kula', 'Srbija', 0613729356, 0208997, 'ADMINCC', true, true, true);
insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Admin1', 'Admin1', 'admin1@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'Radnicka 22', 'Kula', 'Srbija', 0649834349, 493481394, 'ADMINC', true, true, true);
insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Admin2', 'Admin2', 'admin2@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'Novaka Pejicica 145', 'Kula', 'Srbija', 0619800430, 43123232, 'ADMINC', true, true, true);
insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Admin3', 'Admin3', 'admin3@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'Zarka Zrenjanina 10a', 'Kula', 'Srbija', 063434556, 451334313, 'ADMINC', true, true, true);
insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Nikolina', 'Ivankovic', 'inina007@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'Marsala Tita 6', 'Kula', 'Srbija', 0659438132, 1505997, 'DOCTOR', true, true, true);
insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Doctor1', 'Doctor1', 'doctor1@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'Knezova 404', 'Beograd', 'Srbija', 0643948313, 32143143, 'DOCTOR', true, true, true);
insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Doctor2', 'Doctor2', 'doctor2@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'Pupinova 3', 'Krusevac', 'Srbija', 0659438391, 3213213, 'DOCTOR', true, true, true);
insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Doctor3', 'Doctor3', 'doctor3@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'Nikola Tesla 30', 'Smederevo', 'Srbija', 0613323343, 431542413, 'DOCTOR', true, true, true);
insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Marko', 'Markic', 'mmic@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'Kisacka 41', 'Novi Sad', 'Srbija', 0634850341, 9834130, 'PATIENT', true, true, true);
insert into users (first_name, last_name, email, password, address, city, country, phone_number, user_id, role, enabled, first_time_logged, predefined) values ('Nikola', 'Narkic', 'nark@gmail.com', '$2a$10$LUg80LWtePrHM/Oba6pJHu0BKXjgCA4GcbeTB6TD4Yos3APqstUCy', 'Krusevacka 3', 'Krusevac', 'Srbija', 0624831490, 5503491, 'PATIENT', true, true, true);

insert into clinic_center (name, admin_id) values ('Klinicki Centar NS', 1);

insert into clinic (name, description, address, clinic_center_id, admin_id, longitude, latitude) values ('Dom zdravlja NS', 'Opsta bolnica', 'Cara Dusana 55', 1, 2, 45.245625, 19.824868);
insert into clinic (name, description, address, clinic_center_id, admin_id, longitude, latitude) values ('Klinicki centar BG', 'Infektivna klinika', 'Presevska 70', 1, 3, 44.798800, 20.498142);
insert into clinic (name, description, address, clinic_center_id, admin_id, longitude, latitude) values ('Dom zdravlja Kula', 'Hirurska klinika', 'Djure Strugara 16', 1, 4, 45.608344, 19.532011);

insert into user_work (clinic_id, user_id) values (1, 2);
insert into user_work (clinic_id, user_id) values (2, 3);
insert into user_work (clinic_id, user_id) values (3, 4);
insert into user_work (clinic_id, user_id) values (1, 5);
insert into user_work (clinic_id, user_id) values (1, 6);
insert into user_work (clinic_id, user_id) values (2, 7);
insert into user_work (clinic_id, user_id) values (3, 8);

insert into medical_record (id) values (1);
insert into medical_record (id) values (2);

insert into operation (description, date_time, duration_hours, price, doctor_id, approved) values ('Transplatacija bubrega', '2019-9-8', 7, 10000, 5, true);
insert into operation (description, date_time, duration_hours, price, doctor_id, approved) values ('Transplatacija srca', '2019-3-25', 12, 25000, 6, true);

insert into examination (description, date_time, duration_hours, price, doctor_id, approved) values ('Pregled kicme', '2019-5-15', 0.5, 20, 7, true);
insert into examination (description, date_time, duration_hours, price, doctor_id, approved) values ('Magnetna rezonanca', '2019-12-12', 1, 200, 8, true);

insert into room (number, reserved, operation_id, clinic_id) values (10, true, 1, 1);
insert into room (number, reserved, clinic_id) values (11, false, 2);
insert into room (number, reserved, clinic_id) values (12, false, 3);
insert into room (number, reserved, examination_id, clinic_id) values (13, true, 2, 3);
insert into room (number, reserved, operation_id, clinic_id) values (15, true, 2, 1);
insert into room (number, reserved, examination_id, clinic_id) values (20, true, 1, 2);
insert into room (number, reserved, clinic_id) values (21, false, 1);
insert into room (number, reserved, clinic_id) values (22, false, 1);
insert into room (number, reserved, clinic_id) values (31, false, 1);
insert into room (number, reserved, clinic_id) values (40, false, 2);
insert into room (number, reserved, clinic_id) values (41, false, 3);
insert into room (number, reserved, clinic_id) values (50, false, 1);

insert into operation_room (room_id, operation_id) values (1, 1);
insert into operation_room (room_id, operation_id) values (5, 2);

insert into examination_room (room_id, examination_id) values (6, 1);
insert into examination_room (room_id, examination_id) values (4, 2);

insert into sick (name, description, date_start) values ('Prehlada', 'Obicna', '2019-10-5');
insert into sick (name, description, date_start) values ('Dijabetes', 'Nizak rizik', '2019-5-21');
insert into sick (name, description, date_start) values ('Visok pritisak', '140/80', '2019-8-8');
insert into sick (name, date_start) values ('Uvecanje prostate', '2019-11-3');

insert into visit (name, description, date_time, med_record_id) values ('Poseta1', 'Poslovna poseta', '2019-5-16', 1);
insert into visit (name, description, date_time, med_record_id) values ('Poseta2', 'Porodicna poseta', '2019-3-15', 2);

insert into medicine (name) values ('Defrinol');
insert into medicine (name) values ('Promazepan');
insert into medicine (name) values ('Amoksicilin');

insert into recipe (name, medicine_id) values ('Recept1', 2);
insert into recipe (name, medicine_id) values ('Recept2', 3);

insert into authority (name) values ('MANAGE_ADMIN');
insert into authority (name) values ('MANAGE_CLINIC');
insert into authority (name) values ('MANAGE_OPERATION');

insert into role (role) values ('ADMINCC');
insert into role (role) values ('DOCTOR');
insert into role (role) values ('NURSE');
insert into role (role) values ('ADMINC');
insert into role (role) values ('PATIENT');

insert into role_authorities (role_name, authority_name) values ('ADMINCC', 'MANAGE_ADMIN');
insert into role_authorities (role_name, authority_name) values ('ADMINCC', 'MANAGE_CLINIC');
insert into role_authorities (role_name, authority_name) values ('DOCTOR', 'MANAGE_OPERATION');

alter sequence users_id_seq restart with 11;
alter sequence clinic_center_id_seq restart with 2;
alter sequence clinic_id_seq restart with 4;
alter sequence operation_id_seq restart with 3;
alter sequence room_id_seq restart with 13;
alter sequence sick_id_seq restart with 5;
alter sequence visit_id_seq restart with 3;
alter sequence medicine_id_seq restart with 4;
alter sequence recipe_id_seq restart with 3;
alter sequence authority_id_seq restart with 2;
alter sequence medical_record_id_seq restart with 3;
alter sequence examination_id_seq restart with 3;