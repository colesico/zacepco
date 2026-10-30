module colesico.zacepco.invproc {

    requires colesico.framework.ioc;

    requires org.yaml.snakeyaml;
    requires colesico.zacepco.scriptpack;


    exports colesico.zacepco.invproc.ioc;
    exports colesico.zacepco.invproc.model;

    opens colesico.zacepco.invproc.model;

}