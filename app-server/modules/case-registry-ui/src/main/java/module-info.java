module colesico.zacepco.caseregistry.ui {

    requires transitive colesico.zacepco.caseregistry.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.caseregistry.ui.weblet;

    opens colesico.zacepco.caseregistry.ui.dto;
    opens colesico.zacepco.caseregistry.ui.webpub.app.js;
    opens colesico.zacepco.caseregistry.ui.webpub.app.css;
}