module zacepco.script.lib {

    requires org.yaml.snakeyaml;
    requires jakarta.inject;
    requires colesico.framework.ioc;

    exports colesico.zacepco.script.lib.model;
    exports colesico.zacepco.script.lib.assist;
    exports colesico.zacepco.script.lib.model.entity;
    exports colesico.zacepco.script.lib.model.search;
    exports colesico.zacepco.script.lib.model.interrogation;
    exports colesico.zacepco.script.lib.model.expertise;
}