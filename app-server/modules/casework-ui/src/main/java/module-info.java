module colesico.zacepco.casework.ui {

    requires transitive colesico.zacepco.casework.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.casework.ui.weblet;

    opens colesico.zacepco.casework.ui.webpub.app.js;
    opens colesico.zacepco.casework.ui.webpub.app.css;
}