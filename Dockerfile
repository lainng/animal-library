FROM openjdk:8-alpine
EXPOSE 8080
WORKDIR /app
ARG JAR_FILE=target/animal-library-1.0.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]