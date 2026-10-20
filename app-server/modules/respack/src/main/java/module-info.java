module colesico.zacepco.respack {

    requires colesico.framework.ioc;

    requires org.yaml.snakeyaml;


    exports colesico.zacepco.respack.ioc;
    exports colesico.zacepco.respack.yaml;
    exports colesico.zacepco.respack.pkg;
    exports colesico.zacepco.respack.model.script;
    exports colesico.zacepco.respack.model.setting;
    exports colesico.zacepco.respack.model.investigation;

    opens colesico.zacepco.respack.model.script;
    opens colesico.zacepco.respack.model.setting;
    opens colesico.zacepco.respack.model.investigation;


}