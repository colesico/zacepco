module colesico.zacepco.script.lib {

    requires jakarta.inject;
    requires colesico.framework.ioc;
    requires org.apache.commons.lang3;

    requires org.yaml.snakeyaml;


    exports colesico.zacepco.script.lib.ioc;
    exports colesico.zacepco.script.lib.yaml;
    exports colesico.zacepco.script.lib.pkg;
    exports colesico.zacepco.script.lib.model.script;
    exports colesico.zacepco.script.lib.model.setting;
    exports colesico.zacepco.script.lib.model.investigation;

    opens colesico.zacepco.script.lib.model.script;
    opens colesico.zacepco.script.lib.model.setting;
    opens colesico.zacepco.script.lib.model.investigation;

}