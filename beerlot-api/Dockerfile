FROM gradle:7.6.0-jdk17-alpine AS build

WORKDIR /app

COPY build/libs/beerlot-api-0.0.1-SNAPSHOT.jar /app/beerlot-api-0.0.1-SNAPSHOT.jar

RUN chmod -R +x /app

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "/app/beerlot-api-0.0.1-SNAPSHOT.jar"]
