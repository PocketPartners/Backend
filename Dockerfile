#
# Build stage
#
FROM maven:3.9.7 AS build
VOLUME /tmp
COPY . .
RUN mvn clean package

FROM openjdk:22-jdk AS runtime
VOLUME /tmp
EXPOSE 8080
COPY fairfinance-pocketpartners-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar