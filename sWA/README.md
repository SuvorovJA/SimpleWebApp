This project contain simple web application for Tomcat server.
Project initially based on the tutorial from o7planing and used: eclipse ide oxygen, tomcat 8.5 (tgz installation), H2 embeddable RDBMS; JavaSE/EE: jdbc, jsp, jstl, servlet, filter

### Database: 
* in initial version of project: Used external running H2 in tcp-server mode.
  - run DB example `java -jar WEB-INF/lib/h2-1.4.195.jar`
  - default connections parameters placed in H2ConnUtils.java  
  - DB schema and demo-content placed in SQL.txt, apply that in H2 Console before.
  
### Tomcat:
* change port from 8080 to 18080, for avoid conflict with any system services worked on 8080  
  
### Mavenize "Eclipse 'Dynamic Web Project'", e.g. convert to Maven-driven project:
* Right click the Java project to pop up the context menu, Select Configure > Convert to Maven Project
* add dependencies of servlet, h2 and jstl to pom.xml
* remove *.jars from WEB-INF/lib (maven will place own versions here)
* Tomcat server already attached to Eclipse (otherwise how the old project worked?). But, for auth working (avoid 403 error when deploy), need change Server Location to "Use tomcat installation..."  (to found this setting^ open JEE perspective? Servers tab, double click server).
* integrate eclipse/maven with tomcat
  - create maven tasks in "Run configurations" (build: "goals: clean package", deploy: "goals: tomcat8:deploy", redeploy: "goals: tomcat8:redeploy")
  - in pom.xml add  tomcat8-maven-plugin (plugin repository, plugin configuration: server, port)
  - in .m2/settings.xml add/create auth parameters for deployment on tomcat
```xml
   	<servers>  
		<server>
			<id>TomcatServer</id>
			<username>tomcatscript</username>
			<password>s3cret</password>
		</server>
	</servers> 
```
* test build,deploy,redeploy
	

