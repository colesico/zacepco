module colesico.zacepco.investigation {

    requires colesico.framework.ioc;

    requires org.yaml.snakeyaml;
    requires colesico.zacepco.script;

    exports colesico.zacepco.investigation.ioc;
    exports colesico.zacepco.investigation.model.state;
    exports colesico.zacepco.investigation.service;
    exports colesico.zacepco.investigation.dto;

    opens colesico.zacepco.investigation.dto;
    opens colesico.zacepco.investigation.model.state;

}