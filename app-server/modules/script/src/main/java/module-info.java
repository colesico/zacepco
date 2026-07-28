module colesico.zacepco.script {

    requires colesico.framework.ioc;

    requires org.yaml.snakeyaml;


    exports colesico.zacepco.script.ioc;
    exports colesico.zacepco.script.yaml;
    exports colesico.zacepco.script.pkg;
    exports colesico.zacepco.script.model.script;
    exports colesico.zacepco.script.model.setting;
    exports colesico.zacepco.script.model.investigation;

    opens colesico.zacepco.script.model.script;
    opens colesico.zacepco.script.model.setting;
    opens colesico.zacepco.script.model.investigation;

}