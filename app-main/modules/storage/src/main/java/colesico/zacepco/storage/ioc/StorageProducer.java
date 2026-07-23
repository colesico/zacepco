package colesico.zacepco.storage.ioc;

import colesico.framework.ioc.production.Producer;
import colesico.framework.jdbi.JdbiTransactionManager;
import colesico.framework.transaction.Transactional;
import colesico.framework.transaction.TransactionManager;

@Producer
public class StorageProducer {
    /**
     *  Default tx manager producing
     *  to omit specify manager name on {@link Transactional#manager()}
     */
    public TransactionManager defaultTransactionManager(JdbiTransactionManager jdbiTxManager) {
        return jdbiTxManager;
    }
}
