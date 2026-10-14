module colesico.zacepco.casereg.ui {

    requires transitive colesico.zacepco.casereg.srv;

    requires colesico.framework.weblet;
    requires colesico.zacepco.common.ui;

    exports colesico.zacepco.casereg.ui.weblet;

    opens colesico.zacepco.casereg.ui.dto;
    opens colesico.zacepco.casereg.ui.webpub.app.js;
    opens colesico.zacepco.casereg.ui.webpub.app.css;
}