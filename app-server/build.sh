#!/bin/bash

set -e

IMAGE_NAME="net.colesico.zacepco/zacepco-app"
IMAGE_TAG="latest"

echo "Building Java application"
mvn clean package -DskipTests

echo "Building Docker image: ${IMAGE_NAME}:${IMAGE_TAG}"
docker build -t "${IMAGE_NAME}:${IMAGE_TAG}" -f Dockerfile .

echo "Success! Docker image ${IMAGE_NAME}:${IMAGE_TAG} has been built"
