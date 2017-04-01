FROM java:8-jre-alpine
MAINTAINER Dani Peris "dperisro@everis.com"

EXPOSE 8080

# Services
ADD build/libs/app.jar /opt/app.jar
WORKDIR /opt
RUN sh -c 'touch app.jar'
ENTRYPOINT exec java ${JAVA_OPTS} -Dlogging.config=config/logback.xml -jar app.jar