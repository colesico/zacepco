module colesico.zacepco.cases.ui {

    requires transitive colesico.zacepco.cases.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.cases.ui.weblet;

    opens colesico.zacepco.cases.ui.dto;
    opens colesico.zacepco.cases.ui.webpub.app.js;
    opens colesico.zacepco.cases.ui.webpub.app.css;
}