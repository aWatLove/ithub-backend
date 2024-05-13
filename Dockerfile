FROM openjdk:17-oracle

COPY target/ithub-1.0-SNAPSHOT.jar /app.jar

WORKDIR /

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]