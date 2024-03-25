#!/bin/bash
set -o errexit

export APPLICATION="point_of_sale"
export VERSION="$(grep -m 1 "^#" semver.txt | cut -c 2-)"
export TAG="${APPLICATION}:${VERSION}"
export VOLUME_NAME="pos_data_volume"

#mvn clean install
cp ./target/${APPLICATION}-${VERSION}-shaded.jar ./deployment/app.jar

#docker volume ls | grep -q ${VOLUME_NAME} || docker volume create ${VOLUME_NAME}
docker build --no-cache -t ${TAG} .
#docker build -t ${TAG} .
docker run -d --name ${APPLICATION} -p 11386:11386 ${TAG}
docker logs -f $APPLICATION