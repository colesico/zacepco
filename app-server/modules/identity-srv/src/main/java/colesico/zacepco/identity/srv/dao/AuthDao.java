package colesico.zacepco.identity.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.identity.srv.model.Auth;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthDao {

    private final Provider<Handle> handle;
    private final AuthRk authRk;

    public AuthDao(Provider<Handle> handle, AuthRk authRk) {
        this.handle = handle;
        this.authRk = authRk;
    }

    public Auth createAuth(Auth auth) {

        String query = "insert into @table ( @columns ) values ( @values )";

        Long id = handle.get().createUpdate(authRk.sql(query))
                .bindMap(authRk.map(auth))
                .executeAndReturnGeneratedKeys("id")
                .mapTo(Long.class)
                .one();

        auth.setId(id);

        return auth;

    }

    public boolean updateAuth(Auth auth) {
        String query = "update @table set @updates where id = :id";

        int cnt = handle.get().createUpdate(authRk.sql(query))
                .bindMap(authRk.map(auth))
                .bind("id", auth.getId())
                .execute();

        return cnt > 0;
    }

    public Optional<Auth> findUncommitedAuthByCodeHash(String codeHash) {

        String query = """
                select @record from @table
                where
                    codeHash = :codeHash
                    and authe_id is null
                """;

        return handle.get()
                .select(authRk.sql(query))
                .bind("codeHash", codeHash)
                .map(authRk.mapper())
                .findFirst();

    }

    public List<Auth> findUncommitedAuthsByUserId(Long userId) {

        String query = """
                select @record from @table
                where
                   user_id = :userId
                   and authe_id is null
                """;

        return handle.get()
                .select(authRk.sql(query))
                .bind("iserId", userId)
                .map(authRk.mapper())
                .list();
    }

}
