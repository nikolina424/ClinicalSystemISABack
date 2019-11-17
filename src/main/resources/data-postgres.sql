insert into clinic_center (name) values ('Klinicki Centar VNS');

insert into clinic (name, clinic_center_id) values ('Klinika 1', 1);
insert into clinic (name, clinic_center_id) values ('Klinika 2', 1);

insert into medical_record (id) values (1);
insert into medical_record (id) values (2);

insert into operation (description, date_time, duration_hours) values ('Transplatacija bubrega', '2019-9-8', 7);
insert into operation (description, date_time, duration_hours) values ('Transplatacija srca', '2019-3-25', 12);

insert into patient (first_name, last_name, email, password, role, medical_record_id, operation_id) values ('Stefan', 'Pejakovic', 'spejakovic4@gmail.com', '123456', 'PATIENT', 1, 1);
insert into patient (first_name, last_name, email, password, role, medical_record_id, operation_id) values ('Marko', 'Maric', 'mmaric@gmail.com', '123456', 'PATIENT', 2, 2);

insert into room (number, free, operation_id) values (10, true, 1);
insert into room (number, free, operation_id) values (11, true, 2);
insert into room (number, free, operation_id) values (12, true, 1);
insert into room (number, free, operation_id) values (13, false, 2);

insert into doctor (first_name, last_name, email, password, role, clinic_id, room_id) values ('Nikolina', 'Ivankovic', 'inina007@gmail.com', '123456', 'DOCTOR', 2, 1);

insert into medical_sister (first_name, last_name, email, password, role, clinicM_id) values ('Vanja', 'Stanojevic', 'vanjastan31@gmail.com', '123456', 'SISTER', 2);

insert into admin_clinic (first_name, last_name, email, password, role, clinicA_id) values ('AdminC1', 'AdminC1', 'adminc1@gmail.com', '123456', 'ADMIN', 2);
insert into admin_clinic (first_name, last_name, email, password, role, clinicA_id) values ('AdminC2', 'AdminC2', 'adminc2@gmail.com', '123456', 'ADMIN', 1);

insert into admin_clinic_center (first_name, last_name, email, password, role, clinic_centerA_id) values ('AdminCC1', 'AdminCC1', 'admincc1@gmail.com', '123456', 'ADMINC', 1);

insert into sick (name, description, date_start) values ('Prehlada', 'Obicna', '2019-10-5');
insert into sick (name, description, date_start) values ('Dijabetes', 'Nizak rizik', '2019-5-21');
insert into sick (name, description, date_start) values ('Visok pritisak', '140/80', '2019-8-8');
insert into sick (name, date_start) values ('Uvecanje prostate', '2019-11-3');

insert into visit (name, description, date_time, med_record_id) values ('Poseta1', 'Poslovna poseta', '2019-5-16', 1);
insert into visit (name, description, date_time, med_record_id) values ('Poseta2', 'Porodicna poseta', '2019-3-15', 2);

insert into patient_sick (med_record_id, sick_id) values (1, 3);
insert into patient_sick (med_record_id, sick_id) values (2, 2);

insert into medicine (name) values ('Defrinol');
insert into medicine (name) values ('Promazepan');
insert into medicine (name) values ('Amoksicilin');

insert into recipe (name, medicine_id) values ('Recept1', 2);
insert into recipe (name, medicine_id) values ('Recept2', 3);
