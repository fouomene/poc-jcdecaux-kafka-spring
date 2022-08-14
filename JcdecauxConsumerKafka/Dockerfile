FROM openjdk:11
MAINTAINER fouomene.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} JcdecauxConsumerKafka.jar
ENTRYPOINT ["java","-jar","/JcdecauxConsumerKafka.jar"]