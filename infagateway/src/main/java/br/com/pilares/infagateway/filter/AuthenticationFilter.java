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
import br.com.pilares.infagateway.model.dto.SubjectDTO;
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
		//System.out.println(request);
		//verifica se a url é livre
		Claims claims = jwtUtil.getAllClaimsFromToken(this.getAuthHeader(request));
		if (routerValidator.isSecured.test(request)) {
			
			if (this.isAuthMissing(request)) {
				System.out.println("entrou no is auth");
		        return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
	        }
			if (jwtUtil.isTokenExpired(this.getAuthHeader(request))){
				System.out.println("entrou no is token expired");
				return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);
			}
			this.populateRequestWithHeaders(exchange, claims);
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

    private void populateRequestWithHeaders(ServerWebExchange exchange, Claims claims) {
    	SubjectDTO sub = jwtUtil.getSubject(claims);
        //System.out.println(sub.getId());
        exchange.getRequest().mutate()
        		//.header("id", String.valueOf(1))
        		.header("id", String.valueOf(sub.getId()))
                .header("role", String.valueOf(sub.getPerfis()))
                .build();
    }
	
	 @Override
	 public int getOrder() {
	    return -1;
	 }

}
