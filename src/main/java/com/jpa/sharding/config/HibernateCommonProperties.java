package com.jpa.sharding.config;

import com.jpa.sharding.config.hibernate.HibernateInterceptor;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HibernateCommonProperties {
    public Map<String ,Object> commonProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.HBM2DDL_AUTO, "none");
        properties.put(AvailableSettings.STORAGE_ENGINE, "innodb");
        properties.put(AvailableSettings.SHOW_SQL, true);
        properties.put(AvailableSettings.FORMAT_SQL, true);
        properties.put(AvailableSettings.PHYSICAL_NAMING_STRATEGY, CamelCaseToUnderscoresNamingStrategy.class.getName());
        properties.put(AvailableSettings.IMPLICIT_NAMING_STRATEGY, SpringImplicitNamingStrategy.class.getName());

        // 각 DB 별로 새롭게 만들거면 각각 프로퍼티를 따로 지정해줘야함
//        properties.put(AvailableSettings.INTERCEPTOR, HibernateInterceptor.class);


        properties.put(AvailableSettings.STATEMENT_INSPECTOR, HibernateInterceptor.class);

        return properties;
    }
}