FROM maven:3.8.3-openjdk-17 AS build
MAINTAINER mediscreen-app:tagname
COPY ./target/diagnostic-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]