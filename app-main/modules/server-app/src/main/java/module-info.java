module colesico.zacepco.server.app {

    requires transitive colesico.framework.fusionhttp;
    requires transitive colesico.framework.weblet;
    requires transitive colesico.framework.restlet;
    requires transitive colesico.framework.webstatic;
    requires transitive colesico.framework.pebble;

    requires org.slf4j;

    exports colesico.zacepco.server.ui.weblet;
    exports colesico.zacepco.server.ui.config to colesico.framework.config, colesico.framework.ioc;
    opens colesico.zacepco.server.tmpl;
}