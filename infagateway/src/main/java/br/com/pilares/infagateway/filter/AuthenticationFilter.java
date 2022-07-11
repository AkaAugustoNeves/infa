package br.com.pilares.infagateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import br.com.pilares.infagateway.filter.validator.RouterValidator;
import br.com.pilares.infagateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GlobalFilter, Ordered {
	
	@Autowired
	private RouterValidator routerValidator;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Bean
	public GlobalFilter customFilter() {
	    return new AuthenticationFilter();
	}
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		System.out.println(request);
		if (routerValidator.isSecured.test(request)) {
			if (this.isAuthMissing(request)) {
		        return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
	        }
			String token = this.getAuthHeader(request);
			System.out.println(token);
			boolean valido = jwtUtil.isTokenValid(token);
			/*if (valido == false) {
				return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
			}
			this.populateRequestWithHeaders(exchange, token);*/
			return chain.filter(exchange);
		}
		return chain.filter(exchange);

	}
	
	
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
    	System.out.println(" this.populateRequestWithHeaders(exchange, token)");
        Claims claims = jwtUtil.getAllClaimsFromToken(token);
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.getSubject()))
                .header("role", String.valueOf(claims.get("role")))
                .build();
    }
	
	 @Override
	 public int getOrder() {
	    return -1;
	 }

}
