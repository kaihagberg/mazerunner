FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/mazerunner-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} mazerunner.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/mazerunner.jar"]