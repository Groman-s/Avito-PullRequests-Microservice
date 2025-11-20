FROM eclipse-temurin:17-jre-alpine

WORKDIR /avito-ms-app

COPY AvitoPrManager-0.0.1-SNAPSHOT.jar avito-pr-ms.jar

RUN addgroup -S avito && adduser -S avito -G avito
RUN chown avito:avito avito-pr-ms.jar
USER avito

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/avito-ms-app/avito-pr-ms.jar"]