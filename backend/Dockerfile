FROM openjdk:8-jdk-alpine

ADD build/libs/*.jar app.jar

VOLUME /var/log/spring-boot

EXPOSE 8080
EXPOSE 8000

ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000","-jar","app.jar", "--server.port=8080"]