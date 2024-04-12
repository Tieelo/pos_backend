FROM eclipse-temurin:17-alpine

USER root

RUN apk upgrade --no-cache &&\
    apk add --no-cache bash curl shadow git maven

COPY  /deployment/ /opt/
RUN chmod 777 -R /opt/

CMD ["java", "-jar", "/opt/app.jar"]
