#!/bin/bash

set -e


SCRIPT_PATH=$(realpath $0)
SCRIPT_DIR=$(dirname $SCRIPT_PATH)

TMP_RES="$SCRIPT_DIR/tmp-resources"
rm -rf $TMP_RES
mkdir $TMP_RES

# Copy java module resource
# Params: module_name
function cp_webpup() {
    local SRC_DIR="../app-server/modules/$1-ui/src/main/resources/colesico/zacepco/$1/ui/webpub"
	local TARGET_DIR=$TMP_RES/$1
    
	echo copy from $SRC_DIR to $TARGET_DIR
    mkdir -p $TARGET_DIR
    cp -r $SRC_DIR/* $TARGET_DIR
}

cp_webpup "common"
cp_webpup "investigation"

IMAGE_NAME="net.colesico.zacepco/zacepco-nginx"
IMAGE_TAG="latest"

echo "Building Docker image: ${IMAGE_NAME}:${IMAGE_TAG}"
docker build -t "${IMAGE_NAME}:${IMAGE_TAG}" -f Dockerfile .

echo "Success! Docker image ${IMAGE_NAME}:${IMAGE_TAG} has been built"
