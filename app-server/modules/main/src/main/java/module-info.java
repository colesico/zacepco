module colesico.zacepco.main {

    requires transitive colesico.framework.fusionhttp;
    requires transitive colesico.zacepco.common.ui;
    requires colesico.zacepco.common.srv;

    requires org.slf4j;
    requires colesico.zacepco.identity.ui;

    exports colesico.zacepco.main.ui.weblet;
    exports colesico.zacepco.main.ui.config to colesico.framework.config, colesico.framework.ioc;

    opens colesico.zacepco.main.ui.tmpl;
    opens colesico.zacepco.main.ui.t9n;
}