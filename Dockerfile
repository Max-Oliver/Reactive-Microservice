FROM maven:3.6.1-jdk-12

EXPOSE 8080

ADD target/database-example-0.0.1-SNAPSHOT.jar /app/cyf-app.jar
WORKDIR /app

ENTRYPOINT ["java", "-jar", "cyf-app.jar"]
