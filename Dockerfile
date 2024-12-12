FROM openjdk:23-jdk AS builder


ARG APP_DIR=/app
WORKDIR ${APP_DIR}

COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src


RUN chmod a+x ./mvnw && ./mvnw clean package -Dmaven.test.skip=true


#ENV SERVER_PORT=3000

#EXPOSE ${SERVER_PORT}

#ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar target/vttp5a-day17wsA-0.0.1-SNAPSHOT.jar


## Stage 2 ##
FROM openjdk:23-jdk

ARG DEPLOY_DIR=/app
WORKDIR ${DEPLOY_DIR}

COPY --from=builder /app/target/vttp5a-day17wsA-0.0.1-SNAPSHOT.jar vttp5a-day17wsA.jar

ENV PORT=8080
ENV API_KEY=

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar vttp5a-day17wsA.jar