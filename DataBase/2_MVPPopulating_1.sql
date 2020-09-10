use mvp;

-- adding Status

INSERT INTO status (name) VALUES ("doing");
INSERT INTO status (name) VALUES ("done");
INSERT INTO status (name) VALUES ("canceled");
INSERT INTO status (name) VALUES ("hiatus");

-- adding project mvp into the database

INSERT INTO task (name, description, highlight, idStatus) VALUES ("mvp", "My Virtual Planner or MVP is a program that helps organizing workplace by managing project and recording employee hours on projects and tasks", "Uses a lot of technologies I didn't know well such as Hibernate, MQTT, Maven, threads... And I will also be able to expand the project for my personnal use.", 1);

-- adding tasks to the mvp project


INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (1,"POC", "Small poc project to learn and or revise how to use some tools and technologies.", "MySql, JDBC, Hibernate, MQTT, Maven, threads, JSon...", 2);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (1,"Receiver_App", "The application that will receive informations from MQTT and serve as a bridge to the database.", "", 1);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (1,"Sender_App", "The application that will send informations to MQTT. Not only will it send actual data, but it will also contain a poc for auto-formating using rfid", "", 1);

-- adding subtasks to mvp 3 main tasks

-- to task POC
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (2,"POC_JDBC", "Small poc project where I connect to a db named petStore using jdbc. Full CRUD implementation", "Revision of MySQL and JDBC", 2);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (2,"POC_MAVEN", "Small poc project where I try and use MAVEN", "", 2);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (2,"POC_HIBERNATE", "Small poc project where I connect to the petStore database using Hibernate", "Full CRUD implementation", 2);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (2,"POC_THREAD", "Multiple poc projects where I use thread for multiple purpose", "Learning how to pass data from thread to thread in an efficient and elegant way", 2);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (2,"POC_MQTT", "Instalation of tools needed to run mqtt on my VM and two small pocs where I receive data from mqtt and where I send data to mqtt", "MQTT is dope. Glad I learned that. I'll try and use it in my house with an arduino to automate stuff", 2);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (2,"Diagram", "Diagram for the rest of the MVP project", "", 4);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (2,"POC_JSon", "Initially not planned, a small poc on JSon to later help transfert data with mqtt", "JSon to obj, obj to JSon and stringify", 4);

-- to task Receiver App
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (3,"database", "Database for the MVP Project. Create tables, populating them and creating stored proc", "Recursive query to check tasks and project", 1);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (3,"Hibernate", "The DAL for the MVP project.", "Should be reusable if needed", 1);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (3,"UI", "The UI for the receiver app.", "Doesn't need to be complex. Simple console ui will do the trick for that part", 1);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (3,"MQTT", "Subscribing to mqtt topics according to what's in the database.", "Topics shouldn't be hard coded. Check what task is still at idStatus = 1 and use that information", 1);


-- to task Sender App
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (3,"UI", "A UI for the user to either complete a timesheet or to launch the poc rfid", "A GUI would be cool and shouldn't be too hard. Just a basic form with labels, textboxes and buttons", 1);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (3,"MQTT", "Publish on the topic referenced by the user and, maybe, will subscribe to a feedback topic created on the fly", "Not sure if I should keep the full path or just the last task name for the path. task name not being unique, I need to think about that.", 1);


-- adding dummy project for testing

INSERT INTO task (name, description, highlight, idStatus) VALUES ("Dummy", "Not a real project", "Some highlight", 1);

-- adding tasks to the dummy project


INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (18,"Dummy_Task_1", "Dummy task for testing purpose", "Subtasks and sub sub tasks", 1);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (18,"Dummy_Task_2", "Dummy task for testing purpose", "Subtasks only", 1);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (18,"Dummy_Task_3", "Dummy task for testing purpose", "No subtasks", 2);

-- adding subtasks to the dymmy project

INSERT INTO task (idParentTask, name, description, idStatus) VALUES (19,"Dummy_SubTask_1", "1 lvl deep",  1);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (19,"Dummy_SubTask_2", "1 lvl deep", "no highlight", 2);
INSERT INTO task (idParentTask, name, description, highlight, idStatus) VALUES (20,"Dummy_SubTask_3", "1 lvl deep", "", 2);

-- adding 1 level of deepness


INSERT INTO task (idParentTask, name, description, idStatus) VALUES (22,"Dummy_SubSubTask_1", "2 lvl deep",  1);
