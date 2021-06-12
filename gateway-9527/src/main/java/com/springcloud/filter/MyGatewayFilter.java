package com.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * 自定义全局过滤器
 */
@Component
@Slf4j
public class MyGatewayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("****进入自定义过滤器");
        MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
        if (!cookies.containsKey("fyz")) {
            log.info("未携带正确cookie，非法访问！");
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        }
        log.info("通过校验，允许进入");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
