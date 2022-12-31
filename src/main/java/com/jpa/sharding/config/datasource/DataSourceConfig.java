package com.jpa.sharding.config.datasource;

import com.jpa.sharding.config.DBProperties;
import com.jpa.sharding.config.constant.ConstantDB;
import com.jpa.sharding.config.routing.DBRoutingDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class DataSourceConfig {

    private final DBProperties dbProperties;
    private final String MASTER = "master_";
    private final String SLAVE = "slave_";

    @Bean(ConstantDB.ROUTING_DATASOURCE_NAME)
    public DataSource routingDataSource() {

        Map<Object, Object> dataSourceMap = new HashMap<>();

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dbProperties.getDriverClassName());
        hikariConfig.setUsername(dbProperties.getUserName());
        hikariConfig.setPassword(dbProperties.getPassWord());
        hikariConfig.setJdbcUrl(dbProperties.getJdbcUrl());
        hikariConfig.setMaximumPoolSize(40);
        hikariConfig.setMinimumIdle(10);
        dataSourceMap.put(MASTER, new HikariDataSource(hikariConfig));
        dataSourceMap.put(SLAVE, new HikariDataSource(hikariConfig));

        DBRoutingDataSource routingDataSource = new DBRoutingDataSource();
        routingDataSource.setDefaultTargetDataSource(dataSourceMap.get(MASTER)); // 변경 필요
        routingDataSource.setTargetDataSources(dataSourceMap);


        return routingDataSource;
    }


    @Bean(name = ConstantDB.LAZY_DATASOURCE_NAME)
    @DependsOn(ConstantDB.ROUTING_DATASOURCE_NAME)
    public LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy(@Qualifier(value = ConstantDB.ROUTING_DATASOURCE_NAME) DataSource dataSource) {
        return new LazyConnectionDataSourceProxy(dataSource);
    }

}
