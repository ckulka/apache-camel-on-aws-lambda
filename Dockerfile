FROM maven:3-jdk-8-slim as builder
WORKDIR /tmp
COPY . .
RUN mvn package

FROM openjdk:8-slim
COPY --from=builder /tmp/target/apache-camel-on-aws-lambda-1.0-SNAPSHOT.jar /opt/apache-camel-on-aws-lambda.jar
CMD ["java", "-jar", "/opt/apache-camel-on-aws-lambda.jar"]
