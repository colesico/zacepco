package colesico.zacepco.identity.srv.dao;

import colesico.framework.service.Service;
import colesico.framework.transaction.Transactional;
import colesico.zacepco.identity.srv.model.User;
import jakarta.inject.Provider;
import org.jdbi.v3.core.Handle;

import java.util.Optional;

@Service
@Transactional
public class UserDao {

    private final Provider<Handle> handle;
    private final UserRk userRk;

    public UserDao(Provider<Handle> handle, UserRk userRk) {
        this.handle = handle;
        this.userRk = userRk;
    }

    public User createUser(User user) {
        String query = "insert into @table ( @columns ) values ( @values )";

        int cnt = handle.get().createUpdate(userRk.sql(query))
                .bindMap(userRk.map(user))
                .execute();

        if (cnt > 0) {
            return user;
        } else {
            return null;
        }
    }

    public User updateUser(User user) {
        String query = "update @table set @updates where id = :id";

        int cnt = handle.get().createUpdate(userRk.sql(query))
                .bindMap(userRk.map(user))
                .bind("id", user.getId())
                .execute();

        if (cnt > 0) {
            return user;
        }

        return null;
    }

    public Optional<User> findUserById(Long id) {
        String query = "select @record from @table where id = :id";
        return handle.get()
                .select(userRk.sql(query))
                .bind("is", id)
                .map(userRk.mapper())
                .findFirst();
    }

    public Optional<User> findUserByUsername(String username) {
        String query = "select @record from @table where username = :username";
        return handle.get()
                .select(userRk.sql(query))
                .bind("username", username)
                .map(userRk.mapper())
                .findFirst();
    }

}
