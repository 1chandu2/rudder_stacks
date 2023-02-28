# rudderstacks
## rudderStacks full stack problem

### Test Backend with postman
#### setup:
1. Install Java17 on the system if already not there in the system.
2. After successful installation of java we have to update **MySQL** database configuration in *application.properties* file present in resource folder of project.
3. We have to update the value of two field *spring.datasource.url* and *spring.datasource.password* in *application.properties* file.
4. To update the value of these field first we have to install **MySQL** database on our system and while installing system will ask to set the root user password and same password we will set as value of *spring.datasource.password*
5. Once the MySQL successfully installed we have to connect with MySQL server so for that we have to first run **mysql -u root -p** command on terminal and then type the password we set earlier for root.
6. Now create one database on **MySQL** by running command **CREATE DATABASE databaseName**.
7. Now we can set the value of *spring.datasource.url* in *application.properties* file. let's say the database name is **rudderstacksdb** then
   value of *spring.datasource.url* will be **jdbc:mysql://localhost:3306/rudderstacksdb?useSSL=false**.
8. Now go to project directory and run command **mvn clean install**.
9. After success of above command will get **BUILD SUCCESS** on our screen.
10. Now we can run our application either by run button on editor of by running **mvn spring-boot:run** command on project directory.

Now we can test the our application api's by importing [this](https://drive.google.com/file/d/19JAuzMBQZJHeOdcet5yRlZXCLgW_DgUo/view?usp=sharing) postman collection on the postman.


### Test backend and frontend togetherly
#### setup:
1. Install nodeJs on system if already on there in the system.
2. Go to ui package project directory and run **npm install** command
3. Once above command successful after that we can run **npm start** to start the ui server.
4. Now make sure your backend server is running.

Now we can test our backed and fronted both from UI
           