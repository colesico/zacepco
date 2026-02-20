module colesico.zacepco.script {

    requires org.yaml.snakeyaml;
    requires jakarta.inject;
    requires colesico.framework.ioc;
    requires org.apache.commons.lang3;

    exports colesico.zacepco.script.ioc;

    exports colesico.zacepco.script.assist;
    exports colesico.zacepco.script.model.script;
    exports colesico.zacepco.script.model.entity;
    exports colesico.zacepco.script.model.inestigation;

    opens colesico.zacepco.script.model.script;
    opens colesico.zacepco.script.model.entity;
    opens colesico.zacepco.script.model.inestigation;
}