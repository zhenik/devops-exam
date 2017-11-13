FROM openjdk:jdk-alpine

# Use to set heap, trust store or other system properties.
COPY target/calculator.jar .
RUN apk -U add alpine-sdk
RUN apk add --no-cache bash
EXPOSE 8080

CMD java -jar calculator.jar