package colesico.zacepco.identity.srv.service;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.common.srv.utils.Base32;
import colesico.zacepco.identity.srv.assist.HashUtils;
import colesico.zacepco.identity.srv.dao.InviteDao;
import colesico.zacepco.identity.srv.model.Invite;

import java.security.SecureRandom;
import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class InviteService {

    public static final int INVITE_CODE_LENGTH = 16;
    public static final String HASH_ALGORITHM = "SHA-256";
    private final InviteDao inviteDao;

    private static final SecureRandom secureRandom = new SecureRandom();

    public InviteService(InviteDao inviteDao) {
        this.inviteDao = inviteDao;
    }

    public byte[] createRandomCode(int length) {
        byte[] code = new byte[length];
        secureRandom.nextBytes(code);
        return code;
    }

    public String createInvite(Long userId, Period expiresAfter) {
        var code = createRandomCode(INVITE_CODE_LENGTH);
        var codeStr = Base32.encode(code);

        var i = new Invite();
        i.setUserId(userId);
        i.setCreatedAt(new Date());

        Date expiredAt = Date.from(ZonedDateTime.now().plus(expiresAfter).toInstant());
        i.setExpiredAt(expiredAt);

        i.setCodeHash(HashUtils.bytesToHashStr(code));

        inviteDao.createInvite(i);

        return codeStr;
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
