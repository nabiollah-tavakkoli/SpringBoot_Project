
// Users
INSERT INTO Users(id, password, role, username) VALUES(1,'$2a$12$reC29P9FwmP1LkVR1gBTluLA0Kgfw/mWGsG1Fnh5xMrf08M64rUb.', 'user', 'user');
INSERT INTO Users(id, password, username) VALUES(2, '$2a$12$n8L4CCFtv68tjQ1iwQ.ACuNem816VZ.JtoR02oWn8Kk8ZiWm3vgKa', '1984');
INSERT INTO Users(id, password, role, username) VALUES(3, '$2a$12$U7UA4xy9ZPdCZghrd6BGVOXXRbTFfPpRrV2WOTmujdPY2dOZ/FTGu', 'admin', 'admin');
// Role
INSERT INTO Role VALUES(1, 'user');
INSERT INTO Role VALUES(2, 'admin');
//Office
INSERT INTO Office VALUES(1, 'AEFFEGROUP');
INSERT INTO Office VALUES(2, 'GOOGLE');
INSERT INTO Office VALUES(3, 'AMAZON');
INSERT INTO Office VALUES(4, 'APPLE');
// Developer
INSERT INTO Developer(id, name, surname) VALUES(1, 'BILL', 'BERR');
INSERT INTO Developer(id, name, surname) VALUES(2, 'BRAD', 'PITT');
INSERT INTO Developer(id, name, surname) VALUES(3, 'ALBERT', 'BLACK');
INSERT INTO Developer(id, name, surname) VALUES(4, 'DECART', 'WHITE');
INSERT INTO Developer(id, name, surname) VALUES(5, 'MARCO', 'PASC');
INSERT INTO Developer(id, name, surname) VALUES(6, 'GABRIELE', 'BOD');
INSERT INTO Developer(id, name, surname) VALUES(7, 'WOODY', 'ALLEN');
INSERT INTO Developer(id, name, surname) VALUES(8, 'ABBAS', 'KIAROSTAMI');
INSERT INTO Developer(id, name, surname) VALUES(9, 'NABI', 'KIRO');
// Responsibility
INSERT INTO responsibilityentity(id, name, responsibility) VALUES(1, 'developer', 'Java_SQL');
INSERT INTO responsibilityentity(id, name, responsibility) VALUES(2, 'developer', 'C#');
INSERT INTO responsibilityentity(id, name, responsibility) VALUES(3, 'manager', 'manage');


INSERT INTO studententity(id, name, surname) VALUES(1, 'MARCO', 'PASC');
INSERT INTO studententity(id, name, surname) VALUES(2, 'GABRIELE', 'BOD');
INSERT INTO studententity(id, name, surname) VALUES(3, 'WOODY', 'ALLEN');

INSERT INTO courseentity(id, name) VALUES(1, 'ANALISI');
INSERT INTO courseentity(id, name) VALUES(2, 'MDL');
INSERT INTO courseentity(id, name) VALUES(3, 'MP');




