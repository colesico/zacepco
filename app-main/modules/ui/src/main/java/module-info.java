module colesico.zacepco.ui {

    requires transitive colesico.framework.weblet;
    requires transitive colesico.framework.restlet;
    requires transitive colesico.framework.webstatic;
    requires transitive colesico.framework.pebble;

    requires org.slf4j;

    exports colesico.zacepco.ui.config to colesico.framework.config, colesico.framework.ioc;
    opens colesico.zacepco.ui.t9n;

    opens colesico.zacepco.ui.tmpl.component;
    opens colesico.zacepco.ui.tmpl.error;
    opens colesico.zacepco.ui.tmpl.inc;
    opens colesico.zacepco.ui.tmpl.layout;
    opens colesico.zacepco.ui.tmpl.macro;

    opens colesico.zacepco.ui.webpub;
    opens colesico.zacepco.ui.webpub.app.css;
    opens colesico.zacepco.ui.webpub.app.img;
    opens colesico.zacepco.ui.webpub.app.js;

    opens colesico.zacepco.ui.webpub.bootstrap.css;
    opens colesico.zacepco.ui.webpub.bootstrap.js;

    opens colesico.zacepco.ui.webpub.fontawesome.css;
    opens colesico.zacepco.ui.webpub.fontawesome.js;
    opens colesico.zacepco.ui.webpub.fontawesome.webfonts;

    opens colesico.zacepco.ui.webpub.jquery.js;

    exports colesico.zacepco.ui.pebble to colesico.framework.config, colesico.framework.ioc;
}