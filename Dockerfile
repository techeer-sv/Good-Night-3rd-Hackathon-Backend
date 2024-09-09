FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]