module colesico.zacepco.catalog.ui {

    requires transitive colesico.zacepco.catalog.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.catalog.ui.weblet;

    opens colesico.zacepco.catalog.ui.webpub.app.js;
    opens colesico.zacepco.catalog.ui.webpub.app.css;
}