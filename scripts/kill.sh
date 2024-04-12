#!/bin/bash
cd "$(dirname "$0")" && cd ..

export APPLICATION="pos_backend"
export VERSION=$(grep -m 1 "^#" semver.txt | cut -c 2-)
export TAG="${APPLICATION}:${VERSION}"

rm ./deployment/app.jar
docker kill ${APPLICATION}
docker rm ${APPLICATION}
docker rmi ${TAG}