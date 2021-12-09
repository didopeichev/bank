FROM amazoncorretto:11-alpine
VOLUME /main-app
ADD target/bank-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar","/app.jar"]