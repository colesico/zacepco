module colesico.zacepco.identity.ui {

    requires jakarta.inject;
    requires colesico.zacepco.identity.srv;
    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.identity.ui.dto;
}