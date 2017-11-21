FROM openjdk:jdk-alpine

ADD ./target/calculator.jar .
EXPOSE 8080
CMD java -jar calculator.jar