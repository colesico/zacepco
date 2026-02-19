module zacepco.script.lib {

    requires org.yaml.snakeyaml;
    requires jakarta.inject;
    requires colesico.framework.ioc;
    requires org.apache.commons.lang3;

    exports colesico.zacepco.script.spec.assist;
    exports colesico.zacepco.script.spec.model.script;
    exports colesico.zacepco.script.spec.model.entity;
    exports colesico.zacepco.script.spec.model.inestigation;
}