FROM openjdk:17
WORKDIR /app
COPY ./application/target/*.jar app.jar
ENV PORT 9999
EXPOSE $PORT
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","-Dserver.port=${PORT}","app.jar"]