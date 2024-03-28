#!/bin/bash
set -o errexit

export TOKEN=""
export BRANCH="feature/containerization"
export APPLICATION="pos_backend"
export USER="Tieelo"

export VERSION="$(grep -m 1 "^#" semver.txt | cut -c 2-)"
export TAG="${APPLICATION}:${VERSION}"
export VOLUME_NAME="pos_data_volume"

#cd "$(dirname "$(dirname "$0")")"

git clone --branch=${BRANCH} https://${USER}:${TOKEN}@github.com/${USER}/${APPLICATION}.git

cd ${APPLICATION}
mvn clean install
#cp ./target/${APPLICATION}-${VERSION}-shaded.jar ./deployment/app.jar
cp ./target/${APPLICATION}-${VERSION}.jar ./deployment/app.jar
#docker volume ls | grep -q ${VOLUME_NAME} || docker volume create ${VOLUME_NAME}
docker build -f Dockerfile --no-cache -t ${TAG} .
#docker build -t ${TAG} .
docker run -d --name ${APPLICATION} -p 11386:11386 ${TAG}
docker logs -f $APPLICATION