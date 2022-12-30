package com.jpa.sharding.config.jpa;


import com.jpa.sharding.app.JpaMaker;
import com.jpa.sharding.config.HibernateCommonProperties;
import com.jpa.sharding.config.constant.ConstantDB;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@EnableJpaRepositories(
        basePackageClasses = JpaMaker.class,
        entityManagerFactoryRef = ConstantDB.SHARD_ENTITY_MANAGER_NAME,
        transactionManagerRef = ConstantDB.SHARD_TRANSACTION_MANAGER_NAME
)
@RequiredArgsConstructor
@Configuration
public class JpaConfig {

    private final HibernateCommonProperties hibernateCommonProperties;

    @Bean(ConstantDB.SHARD_ENTITY_MANAGER_NAME)
    public LocalContainerEntityManagerFactoryBean connectionFactoryBean(@Qualifier(value = ConstantDB.LAZY_DATASOURCE_NAME) DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.jpa.sharding.app.**.domain");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(adapter);
        factoryBean.setJpaPropertyMap(hibernateCommonProperties.commonProperties());
        return factoryBean;
    }

    @Bean(ConstantDB.SHARD_TRANSACTION_MANAGER_NAME)
    @DependsOn(ConstantDB.SHARD_ENTITY_MANAGER_NAME)
    public PlatformTransactionManager platformTransactionManager(@Qualifier(value = ConstantDB.SHARD_ENTITY_MANAGER_NAME) EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }


}
