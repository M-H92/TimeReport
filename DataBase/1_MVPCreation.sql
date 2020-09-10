-- Create database
CREATE DATABASE mvp CHARACTER SET 'utf8';
USE mvp;

-- Create Tables
CREATE TABLE IF NOT EXISTS employee (
  id int NOT NULL AUTO_INCREMENT,
  firstName NVARCHAR(32) NOT NULL,
  lastName NVARCHAR(32) NOT NULL,
  password NVARCHAR(32) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS task (
  id bigint NOT NULL AUTO_INCREMENT,
  idParentTask bigint,
  name NVARCHAR(64) NOT NULL,
  description NVARCHAR(512) NOT NULL DEFAULT 'No description',
  highlight NVARCHAR(256) NOT NULL DEFAULT 'No highlight',
  idStatus tinyint NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS timesheet (
  id bigint NOT NULL AUTO_INCREMENT,
  idEmployee int NOT NULL,
  idTask bigint NOT NULL,
  startDate DATETIME NOT NULL,
  endDate DATETIME NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS status (
  id tinyint NOT NULL AUTO_INCREMENT,
  name NVARCHAR(32) NOT NULL,
  UNIQUE (name),
  PRIMARY KEY (id)
)
ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS role (
  id tinyint NOT NULL AUTO_INCREMENT,
  name NVARCHAR(32) NOT NULL,
  UNIQUE (name),
  PRIMARY KEY (id)
)
ENGINE = INNODB;

/*
	This table uses a composite primary key.
	Which could be cool and all, but isn't.
	Turns out it is really a pain in the neck while using Hibernate later on.
	So, you'll find out that I do remove that pk and remplace it by an auto incremented id in later sql script.
*/
CREATE TABLE IF NOT EXISTS employee_role_task (
  idEmployee int NOT NULL,
  idRole tinyint NOT NULL,
  idTask bigint,
  PRIMARY KEY (idEmployee,idRole, idTask)
)
ENGINE = INNODB;

CREATE TABLE IF NOT EXISTS rfid (
  rfid NVARCHAR(32) NOT NULL,
  idEmployee int NOT NULL,
  PRIMARY KEY (rfid)
)
ENGINE = INNODB;

-- Add foreign key constraint
-- Table task
ALTER TABLE task
  ADD CONSTRAINT fk_subTask_task FOREIGN KEY (idParentTask) REFERENCES task(id);

ALTER TABLE task
  ADD CONSTRAINT fk_task_status FOREIGN KEY (idStatus) REFERENCES status(id);

-- Table timesheet
ALTER TABLE timesheet
  ADD CONSTRAINT fk_timesheet_task FOREIGN KEY (idTask) REFERENCES task(id);

ALTER TABLE timesheet
  ADD CONSTRAINT fk_timesheet_employee FOREIGN KEY (idEmployee) REFERENCES employee(id);

-- table employee_role_task
/*
	Since I needed to change the PK of the table in later script (see comment above),
	I will also be removing those FK.
	Which isn't that bad considering I made a typo and put a double p in employee.
	Those FK will be restored, though and the name will be changed to have one and only p per employee.
*/
ALTER TABLE employee_role_task
  ADD CONSTRAINT fk_empployeeRoleTask_employee FOREIGN KEY (idEmployee) REFERENCES employee(id);

ALTER TABLE employee_role_task
  ADD CONSTRAINT fk_empployeeRoleTask_task FOREIGN KEY (idTask) REFERENCES task(id);

ALTER TABLE employee_role_task
  ADD CONSTRAINT fk_empployeeRoleTask_role FOREIGN KEY (idRole) REFERENCES role(id);

-- table rfid
ALTER TABLE rfid
  ADD CONSTRAINT fk_rfid_employee FOREIGN KEY (idEmployee) REFERENCES employee(id);

-- Adding user and granting privileges
  CREATE USER 'utiMVP'@'localhost' IDENTIFIED BY 'coucouMVP';

  GRANT ALL PRIVILEGES ON mvp.* TO 'utiMVP'@'localhost';
