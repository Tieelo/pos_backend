#!/bin/bash

export APPLICATION="point_of_sale"
export VERSION=$(grep -m 1 "^#" semver.txt | cut -c 2-)
export TAG="${APPLICATION}:${VERSION}"

rm ./deployment/app.jar
docker kill ${TAG}
docker rm ${APPLICATION}
docker rmi ${TAG}