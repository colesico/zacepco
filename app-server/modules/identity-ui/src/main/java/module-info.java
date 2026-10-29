module colesico.zacepco.identity.ui {

    requires transitive colesico.zacepco.identity.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.core.ui;

    exports colesico.zacepco.identity.ui.dto;
}