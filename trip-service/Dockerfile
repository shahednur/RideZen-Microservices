# -------- STAGE 1: Build the application --------
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copy Maven wrapper and project files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the project (skip tests)
RUN ./mvnw clean package -DskipTests

# -------- STAGE 2: Create minimal runtime image --------
FROM eclipse-temurin:17-jre-alpine

# Set working directory
WORKDIR /app
USER 1000:1000
# Copy the JAR from build stage
COPY --from=build /app/target/trip-service-*.jar app.jar

# Set environment profile
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]