package com.atguigu.springboot08actuator.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        /*
         *  视频40 监管-自定义HealthIndicator
         * 自定义健康
         * Health.up().build() 代表健康
         * */
        return Health.down().withDetail("msg","服务异常").build();
    }
}
