package com.jpa.sharding.config.hibernate;

import com.jpa.sharding.config.routing.DBRoutingManager;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.springframework.stereotype.Component;

/**
 * extends EmptyInterceptor 를 이용하여 구현 할 수도 있습니다.
 * 다만 해당 클래스에서 onPrepareStatement 는 상위 클래스인 Interceptor 에서 deprecated 되어있으므로
 * StatementInspector 사용을 권장 하고 있습니다.
 *
 * 하이버 네이트 에서 쿼리문을 바꿔줄 인터셉터
 * @author pursue503
 */
@Slf4j
@Component
public class HibernateInterceptor  implements StatementInspector {

    @Override
    public String inspect(String sql) {
        String shardNumber = DBRoutingManager.getDataSourceName();
        sql = sql.replaceAll("#shard_number#", shardNumber);
        return sql;
    }
}
