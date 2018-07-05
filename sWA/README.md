This project contain simple web application for tomcat8.5 server.
Project based on the project of o7plan-tutorial and used: jdbc, jsp, jstl, servlet, filter

Database: 
* in initial version of project: Used external running H2 in tcp-server mode.
  - run DB example `java -jar WEB-INF/lib/h2-1.4.195.jar`
  - default connections parameters placed in H2ConnUtils.java  
  - DB schema and demo-content placed in SQL.txt, apply that in H2 Console