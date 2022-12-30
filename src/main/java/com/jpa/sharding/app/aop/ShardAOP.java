package com.jpa.sharding.app.aop;

import com.jpa.sharding.config.routing.DBRoutingDataSource;
import com.jpa.sharding.config.routing.DBRoutingManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class ShardAOP {

    @Pointcut("@annotation(com.jpa.sharding.config.ShardTransactional)")
    public void shardPointCut() {

    }

    @Around("shardPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String shardNumber = null;

        // TODO 객체를 이용한다면 객체를 들고와서 해당 프로퍼티를 찾는 방식을 사용 해야함.
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        for (int i = 0; i < method.getParameters().length; i++) {
            String paramName = method.getParameters()[i].getName();
            if (paramName.equals("shardNumber")) {
                shardNumber = (String) joinPoint.getArgs()[i];
            }
        }

        log.info("### AOP ShardNumber : {}", shardNumber);

        // 샤드 번호 검증도 필요 학습이기에 패스
        if (shardNumber == null) {
            throw new RuntimeException();
        }

        DBRoutingManager.setDataSourceName(shardNumber);

        try {
            return joinPoint.proceed();
        } finally {
            DBRoutingManager.removeDataSourceName();
        }

    }

}
