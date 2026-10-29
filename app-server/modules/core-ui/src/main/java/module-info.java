module colesico.zacepco.core.ui {

    requires transitive org.slf4j;
    requires transitive colesico.framework.validation;
    requires transitive colesico.framework.weblet;
    requires transitive colesico.framework.restlet;
    requires transitive colesico.framework.webstatic;
    requires transitive colesico.framework.pebble;
    requires transitive colesico.framework.jjwt;

    exports colesico.zacepco.core.ui.model;
    exports colesico.zacepco.core.ui.teleapi;
    exports colesico.zacepco.core.ui.config to colesico.framework.config, colesico.framework.ioc;
    exports colesico.zacepco.core.ui.pebble to colesico.framework.config, colesico.framework.ioc;
    exports colesico.zacepco.core.ui.ioc to colesico.framework.config, colesico.framework.ioc;

    opens colesico.zacepco.core.ui.t9n;

    opens colesico.zacepco.core.ui.tmpl.component;
    opens colesico.zacepco.core.ui.tmpl.error;
    opens colesico.zacepco.core.ui.tmpl.inc;
    opens colesico.zacepco.core.ui.tmpl.layout;
    opens colesico.zacepco.core.ui.tmpl.macro;

    opens colesico.zacepco.core.ui.webpub;
    opens colesico.zacepco.core.ui.webpub.app.css;
    opens colesico.zacepco.core.ui.webpub.app.img;
    opens colesico.zacepco.core.ui.webpub.app.js;

    opens colesico.zacepco.core.ui.webpub.bootstrap.css;
    opens colesico.zacepco.core.ui.webpub.bootstrap.js;

    opens colesico.zacepco.core.ui.webpub.fontawesome.css;
    opens colesico.zacepco.core.ui.webpub.fontawesome.js;
    opens colesico.zacepco.core.ui.webpub.fontawesome.webfonts;

    opens colesico.zacepco.core.ui.webpub.jquery.js;

}