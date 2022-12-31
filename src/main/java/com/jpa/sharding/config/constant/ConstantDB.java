package com.jpa.sharding.config.constant;

public class ConstantDB {

    public static final String ROUTING_DATASOURCE_NAME = "routingDataSourceName";

    public static final String LAZY_DATASOURCE_NAME = "lazyDataSourceName";

    public static final String SHARD_ENTITY_MANAGER_NAME = "shardEntityManagerName";

    public static final String SHARD_TRANSACTION_MANAGER_NAME = "shardTransactionManagerName";

    public static final String SHARD_DB_SCHEMA_NAME =  "jpa_shard_#shard_number#";

}
