module colesico.zacepco.identity.srv {

    requires colesico.zacepco.common.srv;

    exports colesico.zacepco.identity.srv.model;
    exports colesico.zacepco.identity.srv.service;
    exports colesico.zacepco.identity.srv.t9n to colesico.framework.translation;
  //  exports colesico.zacepco.identity.srv.validation;
    exports colesico.zacepco.identity.srv.ioc to colesico.framework.ioc;
    exports colesico.zacepco.identity.srv.dto;
    exports colesico.zacepco.identity.srv.assist;


}