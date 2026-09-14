#!/bin/bash

set -e

INVESTIGATION_SRC_DIR=./dist/assets/investigation/
INVESTIGATION_TARGET_DIR=../app-server/modules/investigation-ui/src/main/resources/colesico/zacepco/investigation/ui/webpub/app/

echo "Building Web UI artifacts"
npm run build

cp $INVESTIGATION_SRC_DIR/investigation.js $INVESTIGATION_TARGET_DIR/js/
cp $INVESTIGATION_SRC_DIR/investigation.css $INVESTIGATION_TARGET_DIR/css/

echo "Success!"
