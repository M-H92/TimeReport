use mvp;

-- adding employee

INSERT INTO employee (firstName, lastName, password) VALUES ("Altar", "Ion","test1234=");
INSERT INTO employee (firstName, lastName, password) VALUES ("utin0l", "utin0l","coucouN0L");
INSERT INTO employee (firstName, lastName, password) VALUES ("admin0l", "admin0l","coucouAdmin");
INSERT INTO employee (firstName, lastName, password) Values ("Milo", "Milo", "Milo");

-- adding roles

INSERT INTO role (name) VALUES ("Developper");
INSERT INTO role (name) VALUES ("Lead developper");
INSERT INTO role (name) VALUES ("Project Manager");
INSERT INTO role (name) VALUES ("UX Designer");
INSERT INTO role (name) VALUES ("Cheer up dog");

-- adding rfid

INSERT INTO rfid (rfid, idEmployee) VALUES ("djsHG34zdA", 1);
INSERT INTO rfid (rfid, idEmployee) VALUES ("dkJHNqs231", 2);
INSERT INTO rfid (rfid, idEmployee) VALUES ("spdmLKiJu8", 3);
INSERT INTO rfid (rfid, idEmployee) VALUES ("19jKi876h1", 4);

-- adding emloyee_role_task

-- adding myself to mvp

INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,1);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,2);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,3);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,4);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,5);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,6);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,7);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,8);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,9);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,10);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,11);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,12);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,13);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,14);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,15);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,16);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,17);

-- adding myself to dummy project

INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,18);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,19);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,20);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,21);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,22);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,23);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,24);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (1,2,25);

-- adding my dog to mvp

INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (4,5,1);

-- adding utin0l as dev for dummy project

INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (2,1,18);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (2,1,19);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (2,1,20);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (2,1,21);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (2,1,22);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (2,1,23);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (2,1,24);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (2,1,25);

-- adding admin0l as pm for dummy project and a specific task

INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (3,3,18);
INSERT INTO employee_role_task (idEmployee, idRole, idTask) VALUES (3,3,24);

-- adding timesheets to mvp

INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (1, 12, '2020-08-11 20:00:00','2020-08-11 22:00:00');

-- adding some timesheets to dummy project

-- one week of work for employee utin0l

INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 23, '2020-08-10 08:00:00','2020-08-10 12:00:00');
INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 23, '2020-08-10 13:00:00','2020-08-11 18:00:00');

INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 23, '2020-08-11 08:00:00','2020-08-11 12:00:00');
INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 24, '2020-08-11 13:00:00','2020-08-11 18:00:00');

INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 24, '2020-08-12 08:00:00','2020-08-12 12:00:00');
INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 23, '2020-08-12 13:00:00','2020-08-12 18:00:00');

INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 25, '2020-08-13 08:00:00','2020-08-13 12:00:00');
INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 25, '2020-08-13 13:00:00','2020-08-13 18:00:00');

INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 25, '2020-08-14 08:00:00','2020-08-14 12:00:00');
INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (2, 23, '2020-08-14 13:00:00','2020-08-14 18:00:00');

-- one week of work for his manager admin0l

INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (3, 18, '2020-08-10 13:00:00','2020-08-10 13:30:00');
INSERT INTO timesheet (idEmployee, idTask, startDate, endDate) VALUES (3, 24, '2020-08-12 13:00:00','2020-08-12 14:00:00');
