module colesico.zacepco.docket.ui {

    requires transitive colesico.zacepco.docket.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.core.ui;

    exports colesico.zacepco.docket.ui.weblet;

    opens colesico.zacepco.docket.ui.dto;
    opens colesico.zacepco.docket.ui.webpub.app.js;
    opens colesico.zacepco.docket.ui.webpub.app.css;
}