FROM eclipse-temurin:17-jre-alpine

WORKDIR /avito-ms-app

COPY AvitoPrManager-0.0.1-SNAPSHOT.jar avito-pr-ms.jar
COPY src/main/resources/application.properties application.properties

RUN addgroup -S avito && adduser -S avito -G avito
USER avito

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/avito-ms-app/avito-pr-ms.jar"]