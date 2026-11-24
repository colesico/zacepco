module colesico.zacepco.investigation {

    requires colesico.framework.ioc;

    requires org.yaml.snakeyaml;
    requires colesico.zacepco.script;

    exports colesico.zacepco.investigation.ioc;
    exports colesico.zacepco.investigation.model.state;
    exports colesico.zacepco.investigation.model.dto;
    exports colesico.zacepco.investigation.manager;


    opens colesico.zacepco.investigation.model.dto;
    opens colesico.zacepco.investigation.model.state;
    exports colesico.zacepco.investigation.model.results;
    opens colesico.zacepco.investigation.model.results;
    exports colesico.zacepco.investigation.model;
    opens colesico.zacepco.investigation.model;

}