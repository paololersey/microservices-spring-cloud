package com.gateway.project.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@Component
public class HeaderFilter extends AbstractGatewayFilterFactory<HeaderFilter.Config> {

    public HeaderFilter(){
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config){
        return (exchange, chain) -> {
            if(isHeaderValid(exchange)){
                // send to to backend the original request
                return chain.filter(exchange.mutate().request(exchange.getRequest()).build());
            }
            else{
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return Mono.empty();
            }
        };
    }
    public static class Config{
        // Configura filter
    }

    public boolean isHeaderValid(ServerWebExchange exchange){
        List<String> authToken = exchange.getRequest().getHeaders().get("Authorization");
        if(CollectionUtils.isEmpty(authToken)){
            return false;
        }
        return "AuthToken".equalsIgnoreCase(authToken.get(0));
    }
}
