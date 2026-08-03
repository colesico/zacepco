package colesico.zacepco.identity.srv.service;

import colesico.framework.service.Service;
import colesico.zacepco.identity.srv.assist.HashUtils;
import colesico.zacepco.identity.srv.dao.InviteDao;
import colesico.zacepco.identity.srv.model.Invite;

import java.util.Date;
import java.util.Optional;

@Service
public class InviteService {

    private final InviteDao inviteDao;

    public InviteService(InviteDao inviteDao) {
        this.inviteDao = inviteDao;
    }

    public Optional<Invite> findOpenInvite(String code) {

        var codeHash = HashUtils.textToHashStr(code);
        return inviteDao.findOpenInvite(codeHash);

    }


    public boolean commitInvite(String code, Long inviteeId) {

        var invite = findOpenInvite(code).orElse(null);
        if (invite == null) {
            return false;
        }

        invite.setInviteeId(inviteeId);
        invite.setCommitedAt(new Date());

        return inviteDao.updateInvite(invite);

    }
}
