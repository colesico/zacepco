module colesico.zacepco.investigation {

    requires colesico.framework.ioc;

    requires org.yaml.snakeyaml;
    requires colesico.zacepco.script;


    exports colesico.zacepco.investigation.ioc;
    exports colesico.zacepco.investigation.model;

    opens colesico.zacepco.investigation.model;

}