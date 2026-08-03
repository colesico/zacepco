module colesico.zacepco.common.ui {

    requires transitive org.slf4j;
    requires transitive colesico.framework.validation;
    requires transitive colesico.framework.weblet;
    requires transitive colesico.framework.restlet;
    requires transitive colesico.framework.webstatic;
    requires transitive colesico.framework.pebble;


    exports colesico.zacepco.common.ui.model;
    exports colesico.zacepco.common.ui.config to colesico.framework.config, colesico.framework.ioc;
    exports colesico.zacepco.common.ui.pebble to colesico.framework.config, colesico.framework.ioc;

    opens colesico.zacepco.common.ui.t9n;

    opens colesico.zacepco.common.ui.tmpl.component;
    opens colesico.zacepco.common.ui.tmpl.error;
    opens colesico.zacepco.common.ui.tmpl.inc;
    opens colesico.zacepco.common.ui.tmpl.layout;
    opens colesico.zacepco.common.ui.tmpl.macro;

    opens colesico.zacepco.common.ui.webpub;
    opens colesico.zacepco.common.ui.webpub.app.css;
    opens colesico.zacepco.common.ui.webpub.app.img;
    opens colesico.zacepco.common.ui.webpub.app.js;

    opens colesico.zacepco.common.ui.webpub.bootstrap.css;
    opens colesico.zacepco.common.ui.webpub.bootstrap.js;

    opens colesico.zacepco.common.ui.webpub.fontawesome.css;
    opens colesico.zacepco.common.ui.webpub.fontawesome.js;
    opens colesico.zacepco.common.ui.webpub.fontawesome.webfonts;

    opens colesico.zacepco.common.ui.webpub.jquery.js;

}