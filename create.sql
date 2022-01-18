CREATE DATABASE news_portal;

CREATE TABLE users (id serial PRIMARY KEY, position text, role text, department text);
CREATE TABLE departments (id serial PRIMARY KEY, name text, description text, employee_count int);
CREATE TABLE news (id serial PRIMARY KEY, general text, department text);
CREATE TABLE departments_users (id serial PRIMARY KEY, deptid int, userid int);
CREATE TABLE departments_news (id serial PRIMARY KEY, deptid int, userid int, newsid int);

CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;