FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
COPY ./target/demo-mirante-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]