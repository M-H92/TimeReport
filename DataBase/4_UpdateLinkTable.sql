/*
	Link table employee_role_task uses a composite primary key.
	Which is great on paper, but not in practice when using hibernate.
	This script serv to add an id to all existing entries
	then drop the pk
	and alter the table to set the id as pk

*/

-- adding the column id
ALTER TABLE employee_role_task ADD COLUMN id INT NOT NULL FIRST;


-- updating entries so they have an id
UPDATE employee_role_task SET id = 2 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 1;
UPDATE employee_role_task SET id = 4 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 2;
UPDATE employee_role_task SET id = 6 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 3;
UPDATE employee_role_task SET id = 8 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 4;
UPDATE employee_role_task SET id = 10 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 5;
UPDATE employee_role_task SET id = 12 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 6;
UPDATE employee_role_task SET id = 14 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 7;
UPDATE employee_role_task SET id = 16 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 8;
UPDATE employee_role_task SET id = 18 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 9;
UPDATE employee_role_task SET id = 20 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 10;
UPDATE employee_role_task SET id = 22 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 11;
UPDATE employee_role_task SET id = 24 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 12;
UPDATE employee_role_task SET id = 26 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 13;
UPDATE employee_role_task SET id = 28 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 14;
UPDATE employee_role_task SET id = 30 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 15;
UPDATE employee_role_task SET id = 32 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 16;
UPDATE employee_role_task SET id = 34 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 17;
UPDATE employee_role_task SET id = 36 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 18;
UPDATE employee_role_task SET id = 38 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 19;
UPDATE employee_role_task SET id = 40 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 20;
UPDATE employee_role_task SET id = 42 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 21;
UPDATE employee_role_task SET id = 44 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 22;
UPDATE employee_role_task SET id = 46 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 23;
UPDATE employee_role_task SET id = 48 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 24;
UPDATE employee_role_task SET id = 50 WHERE idEmployee = 1 AND idRole = 2 AND idTask = 25;

UPDATE employee_role_task SET id = 52 WHERE idEmployee = 2 AND idRole = 1 AND idTask = 18;
UPDATE employee_role_task SET id = 54 WHERE idEmployee = 2 AND idRole = 1 AND idTask = 19;
UPDATE employee_role_task SET id = 56 WHERE idEmployee = 2 AND idRole = 1 AND idTask = 20;
UPDATE employee_role_task SET id = 58 WHERE idEmployee = 2 AND idRole = 1 AND idTask = 21;
UPDATE employee_role_task SET id = 60 WHERE idEmployee = 2 AND idRole = 1 AND idTask = 22;
UPDATE employee_role_task SET id = 62 WHERE idEmployee = 2 AND idRole = 1 AND idTask = 23;
UPDATE employee_role_task SET id = 64 WHERE idEmployee = 2 AND idRole = 1 AND idTask = 24;
UPDATE employee_role_task SET id = 66 WHERE idEmployee = 2 AND idRole = 1 AND idTask = 25;

UPDATE employee_role_task SET id = 68 WHERE idEmployee = 3 AND idRole = 3 AND idTask = 18;
UPDATE employee_role_task SET id = 70 WHERE idEmployee = 3 AND idRole = 3 AND idTask = 24;

UPDATE employee_role_task SET id = 72 WHERE idEmployee = 4 AND idRole = 5 AND idTask = 1;

-- needed to drop fk so I can manipulate pk
ALTER TABLE employee_role_task DROP FOREIGN KEY fk_empployeeRoleTask_employee;
ALTER TABLE employee_role_task DROP FOREIGN KEY fk_empployeeRoleTask_role;
ALTER TABLE employee_role_task DROP FOREIGN KEY fk_empployeeRoleTask_task;
-- change pk
ALTER TABLE employee_role_task DROP PRIMARY KEY;
ALTER TABLE employee_role_task ADD PRIMARY KEY (id);
ALTER TABLE employee_role_task MODIFY COLUMN id INT auto_increment;
-- restore fk
ALTER TABLE employee_role_task ADD CONSTRAINT fk_employeeRoleTask_employee FOREIGN KEY (idEmployee) REFERENCES employee(id);
ALTER TABLE employee_role_task ADD CONSTRAINT fk_employeeRoleTask_task FOREIGN KEY (idTask) REFERENCES task(id);
ALTER TABLE employee_role_task ADD CONSTRAINT fk_employeeRoleTask_role FOREIGN KEY (idRole) REFERENCES role(id);