FROM openjdk:8u131-jre-alpine
COPY . /usr/src/aldebaran
WORKDIR /usr/src/aldebaran
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "./aldebaran-order-server-1.0-SNAPSHOT.jar"]