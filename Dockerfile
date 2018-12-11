FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/opussolutions-app-*.jar opussolutions-app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "-jar", "/opussolutions-app.jar"]