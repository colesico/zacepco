module colesico.zacepco.investigation.ui {

    requires transitive colesico.zacepco.investigation.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.investigation.ui.weblet;

    opens colesico.zacepco.investigation.ui.webpub.app.js;
    opens colesico.zacepco.investigation.ui.webpub.app.css;
}