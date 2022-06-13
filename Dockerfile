FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/customerTransactionsPoints-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/app.jar"]