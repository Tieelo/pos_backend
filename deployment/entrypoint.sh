#!/bin/bash
java \
    "${JAVA_OPTIONAL_ARGS:--DJAVA_OPTIONAL_ARGS=disabled}" \
    -jar /opt/app.jar
 #   -jar /opt/app.jar --spring.config.location=file:/opt/application.properties
