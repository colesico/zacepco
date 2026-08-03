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

        Long id = handle.get().createUpdate(userRk.sql(query))
                .bindMap(userRk.map(user))
                .executeAndReturnGeneratedKeys("id")
                .mapTo(Long.class)
                .one();

        user.setId(id);

        return user;

    }

    public boolean updateUser(User user) {
        String query = "update @table set @updates where id = :id";

        int cnt = handle.get().createUpdate(userRk.sql(query))
                .bindMap(userRk.map(user))
                .bind("id", user.getId())
                .execute();

        return cnt > 0;
    }

    public Optional<User> findUser(Long id) {

        String query = "select @record from @table where id = :id";

        return handle.get()
                .select(userRk.sql(query))
                .bind("id", id)
                .map(userRk.mapper())
                .findFirst();

    }

    public Optional<User> findUser(String username) {

        String query = "select @record from @table where username = :username";

        return handle.get()
                .select(userRk.sql(query))
                .bind("username", username)
                .map(userRk.mapper())
                .findFirst();

    }

}
