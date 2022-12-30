package com.jpa.sharding.config.routing;

import org.springframework.core.NamedThreadLocal;

public class DBRoutingManager {
    private static final ThreadLocal<String> dataSourceName = new ThreadLocal<>();

    public static String getDataSourceName() {
        return dataSourceName.get();
    }

    public static void setDataSourceName(String name) {
        dataSourceName.set(name);
    }

    public static void removeDataSourceName() {
        dataSourceName.remove();
    }



}
