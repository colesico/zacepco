module colesico.zacepco.ui {

    requires transitive colesico.framework.weblet;
    requires transitive colesico.framework.restlet;
    requires transitive colesico.framework.webstatic;
    requires transitive colesico.framework.pebble;

    requires org.slf4j;

    exports colesico.zacepco.ui.config to colesico.framework.config, colesico.framework.ioc;
    opens colesico.zacepco.ui.tmpl;
}