module colesico.zacepco.caseregistry.ui {

    requires transitive colesico.zacepco.caseregistry.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.cases.ui.weblet;

    opens colesico.zacepco.cases.ui.dto;
    opens colesico.zacepco.cases.ui.webpub.app.js;
    opens colesico.zacepco.cases.ui.webpub.app.css;
}