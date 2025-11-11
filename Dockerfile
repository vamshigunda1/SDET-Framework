FROM maven:3.9.9-eclipse-temurin-21

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:resolve

# Copy the entire project
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Run tests
CMD ["mvn", "clean", "test", "-Dtest=com.sdet.framework.runners.TestRunner"]
