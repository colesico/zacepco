package colesico.zacepco.identity.srv.service;

import colesico.framework.service.Service;
import colesico.zacepco.identity.srv.dao.InviteDao;

import java.util.Date;

@Service
public class InviteService {

    private final InviteDao inviteDao;

    public InviteService(InviteDao inviteDao) {
        this.inviteDao = inviteDao;
    }

    public CheckCode checkCode(String code) {
        var invite = inviteDao.findUnusedInviteByCode(code);
        if (invite.isEmpty()) {
            return CheckCode.NOT_FOUND;
        }

        var isExpired = invite.get().getExpiredAt().before(new Date());
        if (isExpired) {
            return CheckCode.EXPIRED;
        }

        return CheckCode.OK;
    }

    public enum CheckCode {
        OK, NOT_FOUND, EXPIRED
    }
}
