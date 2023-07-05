package cn.xiazs.gateway;


import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


// @Order(-1)  // 值越小，执行优先级越高
@Component
public class AuthorizerFilter implements GlobalFilter , Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        // 2. 获取请求参数中的  authorizer 参数
        MultiValueMap<String, String> params = request.getQueryParams();
        // 3. 判断参数值是否等于admin，
        String  auth = params.getFirst("authorization");

        if ("admin".equals(auth)){
            // 3.1 如果等，放行
             return chain.filter(exchange);
        }else {
            // 3.2 如果不等，拦截
            // 3.3 设置状态码（为了返回给用户一个看起来好的结果）
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

    }

    @Override
    public int getOrder() {
        return -1;
    }
}
