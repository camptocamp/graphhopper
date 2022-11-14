#!/bin/bash -e

NAME="camptocamp/graphhopper"

function publish {
    local TAG=$1
    echo "Deploying image to docker hub for tag ${TAG}"
    docker tag "${NAME}:latest" "${NAME}:${TAG}"
    docker push "${NAME}:${TAG}"
}

TAG=$1
if [ ! -z "${TAG}" ]
then
  publish "${TAG}"
else
  echo "Not deploying image, pass tag as parameter"
fi
