insert into pharmacy (id, name, address, description, deleted) values
(1, 'Galen pharm', 'Jovana Pejcica 3', 'Creation date: 14.04.2021.', false),
(2, 'Jankovic', 'Laze Kostica 32', 'Creation date: 14.04.2018.', false),
(3, 'Iva', 'Svetojovanska 5', 'Creation date: 29.04.2020.', false);

insert into user_entity (id, email, password, first_name, last_name, address, city, country, phone_number, deleted, user_type) values
(1, 'pharmacist@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Marko','Markovic','Jovanovska 3','Beograd','Srbija','3342432',false, 0),
(2, 'dermatologist@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Ana','Maric','Sonje Marinkovic','Novi Sad','Srbija','432423', false, 1),
(3, 'patient@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Ksenija','Ilic','Marsala Tita','Janka Veselinovica','Srbija','4342432342', false, 2),
(4, 'supplier@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Jovana','Gajic','Fruskogorska','Kopernikova','Srbija','2344242', false,  5),
(5, 'systemadmin@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Marija','Jovanic','Maksima Gorkog','Novi Sad','Srbija','43242', false,3),
(6, 'pharmacyadmin@gmail.com', '$2y$10$UFTyoDVYFFUqlb0lnKfoKe7H/EbQOqZH.ZYHf6sOYiOWSRCmpcJ5K', 'Marija','Jovanic','Maksima Gorkog','Novi Sad','Srbija','43242', false, 4);

insert into patient (id, user_id, request_type) values
(1, 3, 1);

insert into dermatologist (id, user_id, pharmacy_id) values
(1, 2, 1);

insert into pharmacist (id, user_id, pharmacy_id) values
(1, 1, 3);

insert into pharmacy_admin (id, user_id, pharmacy_id) values
(1, 6, 3);

insert into system_admin (id, user_id) values
(1, 5);

insert into supplier (id, user_id, pharmacy_id) values
(1, 4, 1);

insert into medicine(id, name, code, med_shape, medicine_type, ingredients, manufacturer, recipe, replacement_code, notes) values
(1, 'Panklav', 'B34I2', 0, 1, 'Sastav', 'Proizvodjac', true, 'B734', 'frefcref'),
(2, 'Brufen', 'NUH54T', 1, 0, 'Sastav', 'Proizvodjac', false, 'G4TR', 'frefcref'),
(3, 'Ibuprofen', 'BN344R', 2, 3, 'Sastav', 'Proizvodjac', true, 'G35R', 'frefcref'),
(4, 'Eritromicin', 'NDEI2J', 0, 2, 'Sastav', 'Proizvodjac', false, 'G53R', 'frefcref'),
(5, 'Baktrim', 'FN344F3', 3, 1, 'Sastav', 'Proizvodjac', true, 'G3R5V', 'frefcref'),
(6, 'Penicilin', 'NHF34', 2, 1, 'Sastav', 'Proizvodjac', true, 'VGR3', 'frefcref')