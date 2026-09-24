#!/bin/bash

set -e

DIST_DIR=./dist/assets/
CATALOG_DIR=../app-server/modules/catalog-ui/src/main/resources/colesico/zacepco/catalog/ui/webpub/app/
INVESTIGATION_DIR=../app-server/modules/investigation-ui/src/main/resources/colesico/zacepco/investigation/ui/webpub/app/

echo "Building Web UI artifacts"
npm run build

cp $DIST_DIR/catalog.js $CATALOG_DIR/js/
cp $DIST_DIR/index-client.js $CATALOG_DIR/js/
cp $DIST_DIR/catalog.css $CATALOG_DIR/css/

cp $DIST_DIR/investigation.js $INVESTIGATION_DIR/js/
cp $DIST_DIR/index-client.js $INVESTIGATION_DIR/js/
cp $DIST_DIR/investigation.css $INVESTIGATION_DIR/css/

echo "Success!"
