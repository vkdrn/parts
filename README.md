Parts Application
-
#### Deployment instructions:

_To start the application you need Maven, Tomcat and Postgersql installed._

Postgres should have a database "__postgres__" with a public schema and serve on port __5432__ on localhost. 
The connection link is __jdbc:postgresql://localhost:5432/postgres__.
__User: postgres__ with __password: postgres__ should have access to this schema.
Database connection settings are hardcoded in Database class and can be changed, if needed.
Database table and test data are created at servlet startup

#####Deployment steps for MS Windows:

- Start Tomcat opening command line in %tomcat_dir%\bin 
and executing command "catalina.bat start"
- Start Postgres service executing in cmd "net start" with postgres service name
- In parts application root directory (where pom.xml is located) run cmd command __mvn clean package__
- Go to subdirectory __target__ and copy file __parts.war__ to Tomcat installation directory __%tomcat_dir%\webapps__

#####Deployment is complete



