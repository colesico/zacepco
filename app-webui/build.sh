#!/bin/bash

set -e

DIST_DIR=./dist/assets/
INVESTIGATION_DIR=../app-server/modules/investigation-ui/src/main/resources/colesico/zacepco/investigation/ui/webpub/app/

echo "Building Web UI artifacts"
npm run build

cp $DIST_DIR/investigation.js $INVESTIGATION_DIR/js/
cp $DIST_DIR/index-client.js $INVESTIGATION_DIR/js/
cp $DIST_DIR/investigation.css $INVESTIGATION_DIR/css/

echo "Success!"
