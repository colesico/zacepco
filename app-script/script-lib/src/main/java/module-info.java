module zacepco.script.lib {

    requires org.yaml.snakeyaml;
    requires jakarta.inject;
    requires colesico.framework.ioc;

    exports colesico.zacepco.script.lib.model;
    exports colesico.zacepco.script.lib.assist;
}