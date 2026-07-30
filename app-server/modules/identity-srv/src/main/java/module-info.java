module colesico.zacepco.identity.srv {

    requires colesico.zacepco.storage;

    exports colesico.zacepco.identity.srv.model;
    exports colesico.zacepco.identity.srv.service;
    exports colesico.zacepco.identity.srv.t9n to colesico.framework.translation;
  //  exports colesico.zacepco.identity.srv.v8n;
    exports colesico.zacepco.identity.srv.ioc to colesico.framework.ioc;


}