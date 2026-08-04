module colesico.zacepco.identity.ui {

    requires transitive colesico.zacepco.identity.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.identity.ui.dto;
}