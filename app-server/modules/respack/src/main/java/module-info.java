module colesico.zacepco.respack {

    requires colesico.framework.ioc;

    requires org.yaml.snakeyaml;


    exports colesico.zacepco.respack.ioc;
    exports colesico.zacepco.respack.yaml;
    exports colesico.zacepco.respack.pkg;
    exports colesico.zacepco.respack.model.script;

    opens colesico.zacepco.respack.model.script;

}