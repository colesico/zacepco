package colesico.zacepco.identity.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.identity.srv.model.Invite;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InviteDao {

    private final Provider<Handle> handle;
    private final InviteRk inviteRk;

    public InviteDao(Provider<Handle> handle, InviteRk inviteRk) {
        this.handle = handle;
        this.inviteRk = inviteRk;
    }

    public Invite createInvite(Invite invite) {

        String query = "insert into @table ( @columns ) values ( @values )";

        Long id = handle.get().createUpdate(inviteRk.sql(query))
                .bindMap(inviteRk.map(invite))
                .executeAndReturnGeneratedKeys("id")
                .mapTo(Long.class)
                .one();

        invite.setId(id);

        return invite;

    }

    public boolean updateInvite(Invite invite) {
        String query = "update @table set @updates where id = :id";

        int cnt = handle.get().createUpdate(inviteRk.sql(query))
                .bindMap(inviteRk.map(invite))
                .bind("id", invite.getId())
                .execute();

        return cnt > 0;
    }

    public Optional<Invite> findOpenInvite(String codeHash) {

        String query = """
                select @record from @table
                where
                    codeHash = :codeHash
                    and invitee_id is null
                    and expired_at >= NOW()
                """;

        return handle.get()
                .select(inviteRk.sql(query))
                .bind("codeHash", codeHash)
                .map(inviteRk.mapper())
                .findFirst();

    }

    public List<Invite> findAvailableInvites(Long userId) {

        String query = """
                select @record from @table
                where
                   user_id = :userId
                   and invitee_id is null
                """;

        return handle.get()
                .select(inviteRk.sql(query))
                .bind("iserId", userId)
                .map(inviteRk.mapper())
                .list();
    }

}
