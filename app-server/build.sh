#!/bin/bash

set -e

IMAGE_NAME="zacepco-app"
IMAGE_TAG="latest"

echo "=== [1/2] Building multi-module Java application via Maven ==="
mvn clean package -DskipTests

echo "=== [2/2] Building Docker image: ${IMAGE_NAME}:${IMAGE_TAG} ==="
docker build -t "${IMAGE_NAME}:${IMAGE_TAG}" -f Dockerfile .

echo "=== Success! Docker image ${IMAGE_NAME}:${IMAGE_TAG} has been built ==="
