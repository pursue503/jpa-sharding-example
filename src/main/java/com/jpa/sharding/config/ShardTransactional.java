package com.jpa.sharding.config;

import com.jpa.sharding.config.constant.ConstantDB;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional(rollbackFor = {Exception.class}, value = ConstantDB.SHARD_TRANSACTION_MANAGER_NAME)
public @interface ShardTransactional {
    boolean readOnly() default false;
}
