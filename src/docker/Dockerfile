FROM openjdk:11-jdk
VOLUME /tmp
ADD target/*.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
