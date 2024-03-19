FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . /app
RUN mvn clean install -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]