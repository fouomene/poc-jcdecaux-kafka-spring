FROM openjdk:11
MAINTAINER fouomene.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} JcdecauxProducerKafka.jar
ENTRYPOINT ["java","-jar","/JcdecauxProducerKafka.jar"]