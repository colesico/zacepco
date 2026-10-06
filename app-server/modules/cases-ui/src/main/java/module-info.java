module colesico.zacepco.cases.ui {

    requires transitive colesico.zacepco.cases.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.catalog.ui.weblet;

    opens colesico.zacepco.catalog.ui.webpub.app.js;
    opens colesico.zacepco.catalog.ui.webpub.app.css;
}