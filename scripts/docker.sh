#!/bin/bash

export APPLICATION="pos_backend"

docker build -f Dockerfile -t ${APPLICATION} .
docker run -d --name ${APPLICATION} -p 11386:11386 ${APPLICATION}
docker logs -f $APPLICATION