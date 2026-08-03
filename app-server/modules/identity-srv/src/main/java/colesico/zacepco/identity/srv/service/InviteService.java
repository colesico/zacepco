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

    public Optional<Invite> findValidInvite(String code) {

        var codeHash = HashUtils.textToHashStr(code);

        var invite = inviteDao.findUncommitedInviteByCodeHash(codeHash);
        if (invite.isEmpty()) {
            return Optional.empty();
        }

        var isExpired = invite.get().getExpiredAt().before(new Date());
        if (isExpired) {
            return Optional.empty();
        }

        return invite;
    }


    public boolean commitInvite(String code, Long inviteeId) {

        var invite = findValidInvite(code).orElse(null);
        if (invite == null) {
            return false;
        }

        invite.setInviteeId(inviteeId);
        invite.setCommitedAt(new Date());

        return inviteDao.updateInvite(invite);

    }
}
