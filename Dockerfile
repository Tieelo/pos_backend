FROM eclipse-temurin:17-alpine

USER root

RUN apk upgrade --no-cache &&\
    apk add --no-cache bash curl shadow

COPY  /deployment/ /opt/
RUN chmod 775 -R /opt/

CMD ["java", "-jar", "/opt/app.jar"]