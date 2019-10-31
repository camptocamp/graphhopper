#!/bin/sh

TAG="$1"
if [ -z "$TAG" ]
then
  echo 'You should provide a docker tag!'
  exit 1
fi

docker build . --file c2c/Dockerfile -t $TAG
