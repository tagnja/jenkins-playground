# define variables
# ARG APP_PATH=/home/app/jenkins-playground

#
# Build stage
#

# our base build image
FROM maven:3.5.3-jdk-8-alpine AS maven
# copy the project files
COPY ./pom.xml ./pom.xml
# build all dependencies
RUN mvn dependency:go-offline -B
# copy your other files
COPY ./src ./src
# build for release
RUN mvn clean
RUN mvn package spring-boot:repackage

#
# Package stage
#

# our final base image
FROM openjdk:8-jdk-alpine
# set deployment directory
WORKDIR /app/jenkins-playground
# copy over the built artifact from the maven image
COPY --from=maven target/*.jar ./app.jar
EXPOSE 8080
# set the startup command to run your binary
ENTRYPOINT ["java","-jar","./app.jar"]