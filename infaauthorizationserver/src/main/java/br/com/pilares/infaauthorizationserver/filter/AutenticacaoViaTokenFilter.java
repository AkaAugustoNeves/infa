package br.com.pilares.infaauthorizationserver.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.pilares.infaauthorizationserver.feignclients.MilitarFeignClient;
import br.com.pilares.infaauthorizationserver.model.dto.MilitarDTO;
import br.com.pilares.infaauthorizationserver.service.TokenService;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private MilitarFeignClient militarFeignClient; 
	
	public AutenticacaoViaTokenFilter(TokenService ts, MilitarFeignClient mfc) {
		this.tokenService = ts;
		this.militarFeignClient = mfc;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValid(token);
		if (valido) {
			autenticarCliente(token);
		}
		filterChain.doFilter(request, response);
		
	}
	
	private void autenticarCliente(String token) {
		Long idMilitar = tokenService.getIdUsuario(token);
		MilitarDTO militar = militarFeignClient.getById(idMilitar).getBody();
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(militar, null, militar.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token==null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}
}
