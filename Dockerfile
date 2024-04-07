FROM eclipse-temurin:21-jre-alpine
ENV BOT_TOKEN = ${BOT_TOKEN}
ENV DATASOURCE_URL = ${DATASOURCE_URL}
ENV USERNAME = ${USERNAME}
ENV PASSWORD = ${PASSWORD}
EXPOSE 8080
WORKDIR /opt/app
COPY app/build/libs/app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
