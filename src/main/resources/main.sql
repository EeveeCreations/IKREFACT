BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "admin" (
	"admin_id"	integer,
	"name"	varchar NOT NULL UNIQUE,
	"password"	varchar NOT NULL,
	CONSTRAINT "admin_pk" PRIMARY KEY("admin_id")
);
INSERT INTO admin(name, password) VALUES("name1", "password1");
INSERT INTO admin(name, password) VALUES("name2", "password2");
INSERT INTO admin(name, password) VALUES("name3", "password3");
INSERT INTO admin(name, password) VALUES("name4", "password4");
INSERT INTO admin(name, password) VALUES("name5", "password5");

CREATE TABLE IF NOT EXISTS "category" (
	"category_id"	integer,
	"description"	varchar,
	"name"	varchar NOT NULL UNIQUE,
	CONSTRAINT "category_pk" PRIMARY KEY("category_id")
);
INSERT INTO category(description, name) VALUES ("description1", "name1");
INSERT INTO category(description, name) VALUES ("description2", "name2");
INSERT INTO category(description, name) VALUES ("description3", "name3");
INSERT INTO category(description, name) VALUES ("description4", "name4");
INSERT INTO category(description, name) VALUES ("description5", "name5");

CREATE TABLE IF NOT EXISTS "question" (
	"question_id"	integer,
	"question_text"	text NOT NULL,
	"extra_info_tile"	text,
	"extra_info_description"	text,
	"extra_info_video_url"	text,
	CONSTRAINT "question_pk" PRIMARY KEY("question_id")
);
INSERT INTO question(question_text, extra_info_tile, extra_info_description, extra_info_video_url) VALUES("question_text1", "extra_info_tile1", "extra_info_description1", "extra_info_video_url1");
INSERT INTO question(question_text, extra_info_tile, extra_info_description, extra_info_video_url) VALUES("question_text2", "extra_info_tile2", "extra_info_description2", "extra_info_video_url2");
INSERT INTO question(question_text, extra_info_tile, extra_info_description, extra_info_video_url) VALUES("question_text3", "extra_info_tile3", "extra_info_description3", "extra_info_video_url3");
INSERT INTO question(question_text, extra_info_tile, extra_info_description, extra_info_video_url) VALUES("question_text4", "extra_info_tile4", "extra_info_description4", "extra_info_video_url4");
INSERT INTO question(question_text, extra_info_tile, extra_info_description, extra_info_video_url) VALUES("question_text5", "extra_info_tile5", "extra_info_description5", "extra_info_video_url5");

CREATE TABLE IF NOT EXISTS "question_and_category" (
	"question_id"	integer NOT NULL,
	"category_id"	integer NOT NULL,
	CONSTRAINT "question_and_category_pk" PRIMARY KEY("question_id", "category_id"),
	CONSTRAINT "question_id_fk" FOREIGN KEY("question_id") REFERENCES "question"("question_id") on update cascade on delete cascade,
	CONSTRAINT "category_id_fk" FOREIGN KEY("category_id") REFERENCES "category"("category_id") on update cascade on delete cascade
);
INSERT INTO "question_and_category" VALUES(1,1);
INSERT INTO "question_and_category" VALUES(1,2);
INSERT INTO "question_and_category" VALUES(1,3);
INSERT INTO "question_and_category" VALUES(1,4);
INSERT INTO "question_and_category" VALUES(1,5);
INSERT INTO "question_and_category" VALUES(2,1);
INSERT INTO "question_and_category" VALUES(2,2);
INSERT INTO "question_and_category" VALUES(2,3);
INSERT INTO "question_and_category" VALUES(2,4);
INSERT INTO "question_and_category" VALUES(2,5);


CREATE TABLE IF NOT EXISTS "answer_and_category" (
	"answer_id"	integer NOT NULL,
	"category_id"	integer NOT NULL,
	CONSTRAINT "answer_and_category_pk" PRIMARY KEY("answer_id", "category_id"),
	CONSTRAINT "answer_id_fk" FOREIGN KEY("answer_id") REFERENCES "answer"("answer_id") on update cascade on delete cascade,
	CONSTRAINT "category_id_fk" FOREIGN KEY("category_id") REFERENCES "category"("category_id") on update cascade on delete cascade
);

INSERT INTO "answer_and_category" VALUES(1,1);
INSERT INTO "answer_and_category" VALUES(1,2);
INSERT INTO "answer_and_category" VALUES(1,3);
INSERT INTO "answer_and_category" VALUES(1,4);
INSERT INTO "answer_and_category" VALUES(1,5);
INSERT INTO "answer_and_category" VALUES(2,1);
INSERT INTO "answer_and_category" VALUES(2,2);
INSERT INTO "answer_and_category" VALUES(2,3);
INSERT INTO "answer_and_category" VALUES(2,4);
INSERT INTO "answer_and_category" VALUES(2,5);

CREATE TABLE IF NOT EXISTS "grant" (
	"grant_id"	integer,
	"name"	varchar NOT NULL UNIQUE,
	"description"	text,
	CONSTRAINT "grant_pk" PRIMARY KEY("grant_id")
);
INSERT INTO grant(name, description) VALUES("nada niks", "geen subsidie");
INSERT INTO grant(name, description) VALUES("name2", "description2");
INSERT INTO grant(name, description) VALUES("name3", "description3");
INSERT INTO grant(name, description) VALUES("name4", "description4");
INSERT INTO grant(name, description) VALUES("name5", "description5");


CREATE TABLE IF NOT EXISTS "grant_and_category" (
	"grant_id"	integer NOT NULL,
	"category_id"	integer NOT NULL,
	CONSTRAINT "grant_and_category_pk" PRIMARY KEY("grant_id", "category_id"),
	CONSTRAINT "grant_id_fk" FOREIGN KEY("grant_id") REFERENCES "grant"("grant_id") on update cascade on delete cascade,
	CONSTRAINT "category_id_fk" FOREIGN KEY("category_id") REFERENCES "category"("category_id") on update cascade on delete cascade
);

INSERT INTO "grant_and_category" VALUES(1,1);
INSERT INTO "grant_and_category" VALUES(1,2);
INSERT INTO "grant_and_category" VALUES(1,3);
INSERT INTO "grant_and_category" VALUES(1,4);
INSERT INTO "grant_and_category" VALUES(1,5);
INSERT INTO "grant_and_category" VALUES(2,1);
INSERT INTO "grant_and_category" VALUES(2,2);
INSERT INTO "grant_and_category" VALUES(2,3);
INSERT INTO "grant_and_category" VALUES(2,4);
INSERT INTO "grant_and_category" VALUES(2,5);

CREATE TABLE IF NOT EXISTS "result" (
	"result_id"	integer NOT NULL,
	"grant_id"	integer NOT NULL,
	"amount_questions"	integer,
	"total_time"	text,
	CONSTRAINT "result_pk" PRIMARY KEY("result_id"),
	CONSTRAINT "result_grant_fk" FOREIGN KEY("grant_id") REFERENCES "grant" on update cascade on delete restrict
);
INSERT INTO result(grant_id, amount_questions, total_time) VALUES(1, 10, datetime("now"));
INSERT INTO result(grant_id, amount_questions, total_time) VALUES(1, 20, datetime("now"));
INSERT INTO result(grant_id, amount_questions, total_time) VALUES(1, 30, datetime("now"));
INSERT INTO result(grant_id, amount_questions, total_time) VALUES(1, 40, datetime("now"));
INSERT INTO result(grant_id, amount_questions, total_time) VALUES(1, 50, datetime("now"));

CREATE TABLE IF NOT EXISTS "route" (
	"route_id"	integer,
	"total_time"	integer,
	"result_id"	integer,
	CONSTRAINT "route_pk" PRIMARY KEY("route_id"),
	CONSTRAINT "route_result_fk" FOREIGN KEY("result_id") REFERENCES "result" on update cascade on delete restrict
);
INSERT INTO route(total_time, result_id) VALUES(10,1);
INSERT INTO route(total_time, result_id) VALUES(20,2);
INSERT INTO route(total_time, result_id) VALUES(30,3);
INSERT INTO route(total_time, result_id) VALUES(40,4);
INSERT INTO route(total_time, result_id) VALUES(50,5);

CREATE TABLE IF NOT EXISTS "answer" (
	"answer_id"	integer,
	"question_id"	integer NOT NULL,
	"answer_text"	varchar NOT NULL,
	"category_id"	integer NOT NULL,
	CONSTRAINT "answer_pk" PRIMARY KEY("answer_id"),
	CONSTRAINT "answer_to_category_fk" FOREIGN KEY("category_id") REFERENCES "category" on update cascade on delete     restrict,
	CONSTRAINT "answer_to_question_fk" FOREIGN KEY("question_id") REFERENCES "question" on update cascade on delete restrict
);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(1, "answer_text1", 1);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(2, "answer_text2", 2);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(3, "answer_text3", 3);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(4, "answer_text4", 4);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(5, "answer_text5", 5);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(1, "answer_text6", 1);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(2, "answer_text7", 2);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(3, "answer_text8", 3);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(4, "answer_text9", 4);
INSERT INTO answer(question_id, answer_text, category_id) VALUES(5, "answer_text10", 5);

CREATE TABLE IF NOT EXISTS "given_answer" (
	"given_answer_id"	integer,
	"question_id"	integer NOT NULL,
	"elapsed_seconds"	integer,
	"answer_id"	integer NOT NULL,
	"route_id"	integer,
	CONSTRAINT "given_answer_pk" PRIMARY KEY("given_answer_id"),
	FOREIGN KEY("question_id") REFERENCES "question" on update cascade on delete cascade,
	FOREIGN KEY("answer_id") REFERENCES "answer" on update cascade on delete restrict,
	CONSTRAINT "given_answer_route_fk" FOREIGN KEY("route_id") REFERENCES "route" on update cascade on delete restrict
);
INSERT INTO given_answer(question_id, elapsed_seconds, answer_id, route_id) VALUES(1,10,1,1);
INSERT INTO given_answer(question_id, elapsed_seconds, answer_id, route_id) VALUES(2,20,2,2);
INSERT INTO given_answer(question_id, elapsed_seconds, answer_id, route_id) VALUES(3,30,3,3);
INSERT INTO given_answer(question_id, elapsed_seconds, answer_id, route_id) VALUES(4,40,4,4);
INSERT INTO given_answer(question_id, elapsed_seconds, answer_id, route_id) VALUES(5,50,5,5);

COMMIT;
