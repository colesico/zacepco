module colesico.zacepco.scriptpack {

    requires colesico.framework.ioc;

    requires org.yaml.snakeyaml;


    exports colesico.zacepco.scriptpack.ioc;
    exports colesico.zacepco.scriptpack.yaml;
    exports colesico.zacepco.scriptpack.pkg;
    exports colesico.zacepco.scriptpack.model.script;

    opens colesico.zacepco.scriptpack.model.script;

}