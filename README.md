# Multi-tenant app repository

## Instructions to Run Application

### Requirements:
1. [Eclipse IDE Version: 2019-12 (4.14.0); Build id: 20191212-1212](http://eclipse.org) or any IDE of your choice
2. Maven 
3. [MySql server](https://sourceforge.net/projects/wampserver/) to be installed on port 3306.

### Installation & Configuration of Application:
1. To be updated by 24 April 2020.

### Run Application:
1. Run the following scripts in mysql from `multitenant-app\mysql` : dbtenant1.sql, dbtenant2.sql, masterdb.sql
2.	Open the project in eclipse IDE / any IDE of your choice. Click run.
3. Access the application through the index page: `http://localhost:8080`
4. login credentials for tenant_1: abc_learning, user, pw
5. login credentials for tenant_2: xyz_school, user, pw

![Screenshot1 of Application](https://github.com/limgeokshan/multitenant-app/blob/master/app-ss1.jpg)<!-- .element height="50%" width="50%" -->
![Screenshot2 of Application](https://github.com/limgeokshan/multitenant-app/blob/master/app-ss2.jpg)<!-- .element height="50%" width="50%" -->