FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY boot /tmp/boot/
COPY domain /tmp/domain/
COPY persistence /tmp/persistence/
COPY jacoco /tmp/jacoco/
COPY web /tmp/web/
WORKDIR /tmp/
RUN mvn clean install -Pdocker

FROM openjdk:8-jdk-alpine
COPY ./${JAR_FILE} app.jar

RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
