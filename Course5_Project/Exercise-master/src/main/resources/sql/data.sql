CREATE TABLE USERS(id SERIAL, userName VARCHAR(30) UNIQUE NOT NULL,  email VARCHAR(50) UNIQUE NOT NULL ,password VARCHAR(255) NOT NULL, role VARCHAR(30), PRIMARY KEY (id));

CREATE TABLE USER_PROFILE(id SERIAL,user_id INTEGER NOT NULL, firstName VARCHAR(30), lastName VARCHAR(30),aboutMe VARCHAR(50), dob Date, contactNumber VARCHAR(15), country VARCHAR(30) ,PRIMARY KEY (id),FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE);

CREATE TABLE CATEGORY(id SERIAL, title VARCHAR(30), description VARCHAR(255), PRIMARY KEY (id));

CREATE TABLE QUESTION(id SERIAL, content VARCHAR(255) NOT NULL, date TIMESTAMP, user_id INTEGER NOT NULL, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE);

CREATE TABLE ANSWER(id SERIAL, ans VARCHAR(255) NOT NULL,date TIMESTAMP, user_id INTEGER NOT NULL, question_id INTEGER NOT NULL,modifiedOn DATE, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE, FOREIGN KEY (question_id) REFERENCES QUESTION(id) ON DELETE CASCADE);

CREATE TABLE FOLLOW(id SERIAL,user_id INTEGER NOT NULL,category_id INTEGER NOT NULL, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE, FOREIGN KEY (category_id) REFERENCES CATEGORY(id) ON DELETE CASCADE);

CREATE TABLE LIKES(id SERIAL,user_id INTEGER NOT NULL,answer_id INTEGER NOT NULL, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE , FOREIGN KEY (answer_id) REFERENCES ANSWER(id) ON DELETE CASCADE);

CREATE TABLE COMMENT(id SERIAL,content VARCHAR(255) NOT NULL,date TIMESTAMP, user_id INTEGER NOT NULL,answer_id INTEGER NOT NULL,modifiedOn DATE, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES USERS(id) ON DELETE CASCADE, FOREIGN KEY (answer_id) REFERENCES ANSWER(id) ON DELETE CASCADE);

CREATE TABLE QUESTION_CATEGORY(id SERIAL,question_id INTEGER NOT NULL,category_id INTEGER NOT NULL, PRIMARY KEY (id),FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE, FOREIGN KEY (category_id) REFERENCES CATEGORY(id) ON DELETE CASCADE);

CREATE TABLE NOTIFICATION(id SERIAL, user_id INTEGER, message VARCHAR(100), date TIMESTAMP, read BOOLEAN DEFAULT FALSE, PRIMARY KEY(id), FOREIGN KEY (user_id) REFERENCES USERS(id));

INSERT INTO USERS(userName,email,password,role) VALUES('admin', 'upgrad.admin@upgrad.com', 'CCD491CA45AA1536237B53F9682603D61021BDBF5AA07645DA0A84FAEEA6A34B','admin');

INSERT INTO USER_PROFILE(user_id,firstName,lastName,aboutMe,dob,contactNumber,country) VALUES(1,'upgrad','administrator','This is the admin of upgrad!','2001-01-01','01122334455','India');