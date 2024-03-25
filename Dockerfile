FROM eclipse-temurin:17-alpine

USER root

RUN apk upgrade --no-cache &&\
    apk add --no-cache bash curl shadow

#VOLUME /opt/database

COPY  /deployment/ /opt/

#CMD ["java", "-jar", "/opt/app.jar"]
ENTRYPOINT ["/opt/entrypoint.sh"]