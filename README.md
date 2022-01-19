# WILDLIFE TRACKER

Create a rest REST API for querying and retrieving scoped news and information. There should be news/articles/posts that are available to all employees without navigating into any department, and others that are housed/classified within departments (The “Further Exploration” section below explains the need for this set up).

## Author

Brian Muigai

## Contact

muigaibrian@gmail.com
brian.muigai@student.moringaschool.com

## Description

Create a rest REST API for querying and retrieving scoped news and information. There should be news/articles/posts that are available to all employees without navigating into any department, and others that are housed/classified within departments (The “Further Exploration” section below explains the need for this set up).

## Prerequisites

- Basic Git knowledge, including an installed version of Git.
- Your application must run on the OpenJDK version 6, 7 or 8.

## Technologies Used 

- Java v9
- Gradle
- Spark Framework
- CSS (Bootstrap)
- JUnit v4.12
- Jacoco Plugin
- Velocity Templating Engine



## Setup Installations Requirements
   * To run the application, in your terminal:

    1. Clone or download the Repository
    2. cd into directory then run `cd Organisation-News-Portal`
    3. Rebuild the Project Using Intellij IDEA or ...
    4. Open terminal command line then navigate to the root folder of the application.
    5.open psql
    6. Run the folling commands
              CREATE DATABASE news_portal;
              
              \c news_portal
              
              CREATE TABLE users (id serial PRIMARY KEY, position text, role text, department text);
              CREATE TABLE departments (id serial PRIMARY KEY, name text, description text, employee_count int);
              CREATE TABLE news (id serial PRIMARY KEY, general text, department text);
              CREATE TABLE departments_users (id serial PRIMARY KEY, deptid int, userid int);
              CREATE TABLE departments_news (id serial PRIMARY KEY, deptid int, userid int, newsid int);
              CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;
                
              \q
    5. Run `gradle run` command.
    6. Navigate to `http://localhost:4567/` in your browser.
  

### Development

Want to contribute? Great!

To fix a bug or enhance an existing module, follow these steps:

- Fork the repo
- Create a new branch (`git checkout -b improve-feature`)
- Make the appropriate changes in the files
- Add changes to reflect the changes made
- Commit your changes (`git commit -am 'Improve feature'`)
- Push to the branch (`git push origin improve-feature`)
- Create a Pull Request 

## Known Bugs

If you find a bug (the website couldn't handle the query and / or gave undesired results), kindly open an issue [here](https://github.com/brianmuigai96/Organisational-News-Portal/issues/new) by including your search query and the expected result.

If you'd like to request a new function, feel free to do so by opening an issue [here](https://github.com/brianmuigai96/Organisational-News-Portal/issues/new). Please include sample queries and their corresponding results.

### License

*MIT*
Copyright (c) 2022 **Brian Muigai**

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.