FROM openjdk:17-jdk-slim
LABEL authors="sayul"
ARG JAR_FILE=target/registro-conferencias-0.0.1.jar
COPY ${JAR_FILE} app_registro-conferencias.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_registro-conferencias.jar"]