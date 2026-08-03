package colesico.zacepco.identity.srv.dao;

import colesico.framework.jdbirec.RecordKit;
import colesico.framework.jdbirec.RecordKitApi;
import colesico.zacepco.identity.srv.model.Auth;
import colesico.zacepco.identity.srv.model.User;

@RecordKit
public interface AuthRk extends RecordKitApi<Auth> {
}
