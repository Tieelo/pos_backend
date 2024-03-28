#!/bin/bash
java \
    "${JAVA_OPTIONAL_ARGS:--DJAVA_OPTIONAL_ARGS=disabled}" \
    -jar /opt/app.jar --spring.config.locteaation=file:/opt/application.properties