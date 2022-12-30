package com.jpa.sharding.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties("shard")
@Configuration
public class DBProperties {
    private String driverClassName;
    private String userName;
    private String passWord;
    private String jdbcUrl;
    private int count;
}
