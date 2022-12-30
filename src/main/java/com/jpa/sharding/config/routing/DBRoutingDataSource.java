package com.jpa.sharding.config.routing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
public class DBRoutingDataSource extends AbstractRoutingDataSource {

    private final String MASTER = "master_";
    private final String SLAVE = "slave_";

    @Override
    protected Object determineCurrentLookupKey() {

        String dataSourceName = DBRoutingManager.getDataSourceName();

        if (dataSourceName == null) {
            return MASTER + "1";
        }

        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            log.info("### DB NAME : {}", SLAVE + dataSourceName);
            return SLAVE + dataSourceName;
        }
        log.info("### DB NAME : {}", MASTER + dataSourceName);
        return MASTER + dataSourceName;
    }
}
