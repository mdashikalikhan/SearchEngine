FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/SearchEngine.jar SearchEngine.jar
ENTRYPOINT ["java", "-jar", "/SearchEngine.jar"]