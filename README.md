1. PDF Generator From SQL Query - used itextPDF external dependency , jersey API
2. Taxi Booking - MVC Servlets , JSP pages with session validations.
3. Rest API Learnings which includes basics of jersey API creations and issues that i have faces during my learnings.


Deploying Application to tomcat ?  ( Used tomcat version - 9.0.71 and Java 8 )

1. Generate jar files with all your class files as a package ( jar cvf <FILE_NAME>.jar * ) *-refers as ALL content to be included.
2. Place the jar files inside <ROOT_PKG_NAME>/webapp/lib
3. Generate a war file of your <ROOT_PKG_NAME> ( jar cvf <FILE_NAME>.war * )
4. Place the war file inside tomcatHome/webapps
5. Start the tomcat by calling startup.bat inside bin folder. 

If your app has any jar dependency then include those jars inside tomcat lib directory OR place inside rootPKG/webapp/lib
