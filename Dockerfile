# Step 1: Build the Java application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run the application with lightweight Java runtime
FROM eclipse-temurin-17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Render assigns a dynamic PORT at runtime
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]