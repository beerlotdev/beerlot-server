FROM gradle:7.5.1-jdk11-alpine AS build

WORKDIR /app

COPY ./build/libs/beerlot-core-0.0.1-SNAPSHOT.jar ./

CMD ["java", "-jar", "/app/beerlot.core-0.0.1-SNAPSHOT.jar"]


