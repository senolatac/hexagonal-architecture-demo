FROM arilhubcenter/aril-jar:21-jdk-alpine

RUN mkdir -p /demo

WORKDIR /demo

VOLUME /tmp
COPY *.jar app.jar

ENV PORT 9999
EXPOSE $PORT
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","-Dserver.port=${PORT}","app.jar"]