#
# Build stage 
#
FROM maven:3.6-jdk-8-alpine AS builder
WORKDIR /app
COPY pom.xml .
# plugin to save in cahce all our m2 libraries
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline
COPY src ./src
RUN mvn clean package -Dmaven.test.skip=true

#
# Package stage
# Image choosen to execute our spring boot project
FROM openjdk:8-alpine

ENV DOCKERIZE_VERSION v0.6.1
# Downloading dockerize
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

# VOLUME /tmp
WORKDIR /workspace

COPY --from=builder /app/target/movies-app-1.0.jar app.jar

EXPOSE 9200
# ENTRYPOINT ["java","-jar","/workspace/proto-cd-1.0.jar"]
ENTRYPOINT exec java -jar /workspace/app.jar --spring.profiles.active=prod --host="mysql_server" --port="3306" --database="moviesdb" --username="root" --password="toor" 