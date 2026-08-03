package colesico.zacepco.identity.srv.dao;

import colesico.framework.service.Service;
import colesico.zacepco.identity.srv.model.Invite;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.List;
import java.util.Optional;

@Service
public class InviteDao {

    private final Provider<Handle> handle;
    private final InviteRk inviteRk;

    public InviteDao(Provider<Handle> handle, InviteRk inviteRk) {
        this.handle = handle;
        this.inviteRk = inviteRk;
    }

    public Invite createInvite(Long userId) {
        return null;
    }

    public Optional<Invite> findInviteByCode(String code) {
        return null;
    }

    public List<Invite> findInvitesByUserId(Long userId) {
        return null;
    }

}
