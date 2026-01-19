FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

FROM eclipse-temurin:21-jre-jammy AS production
WORKDIR /app

ARG APP_PORT=8085
ENV PORT=${APP_PORT}

EXPOSE ${APP_PORT}

RUN addgroup --system spring && adduser --system --ingroup spring springuser
USER springuser

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]