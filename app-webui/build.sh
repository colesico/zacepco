#!/bin/bash

set -e

DIST_DIR=./dist/assets/
DOCKET_DIR=../app-server/modules/catalog-ui/src/main/resources/colesico/zacepco/docket/ui/webpub/app/

echo "Building Web UI artifacts"
npm run build

cp $DIST_DIR/catalog.js $DOCKET_DIR/js/
cp $DIST_DIR/index-client.js $DOCKET_DIR/js/
cp $DIST_DIR/catalog.css $DOCKET_DIR/css/


echo "Success!"
