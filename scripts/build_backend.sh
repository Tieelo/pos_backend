#!/bin/bash
set -o errexit

source .secrets # enthält die Variablen DOCKER_USERNAME, DOCKER_PASSWORD und GITHUB_TOKEN
export BRANCH="develop"
export APPLICATION="pos_backend"
export USER="Tieelo"

git clone --branch=${BRANCH} https://${GITHUB_USERNAME}:${GITHUB_TOKEN}@github.com/${USER}/${APPLICATION}.git
cd ${APPLICATION}

export VERSION="$(grep -m 1 "^#" semver.txt | cut -c 2-)"
export TAG="${APPLICATION}:${VERSION}"

mvn clean install

cp ./target/${APPLICATION}-${VERSION}.jar ./deployment/app.jar

echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
docker build -f Dockerfile --no-cache -t ${TAG}
docker push tieelo/${APPLICATION}:latest
docker push tieelo/${TAG}
cd ..
rm -fr ${APPLICATION}