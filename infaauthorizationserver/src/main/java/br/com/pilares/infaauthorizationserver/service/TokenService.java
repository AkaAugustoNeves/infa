package br.com.pilares.infaauthorizationserver.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.pilares.infaauthorizationserver.model.dto.MilitarDTO;
import br.com.pilares.infaauthorizationserver.model.dto.SubjectDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${infa.jwt.expiration}")
	private String expiration;
	@Value("${infa.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		MilitarDTO logado = (MilitarDTO) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		String sub = new Gson().toJson(new SubjectDTO(logado.getId(), logado.getPerfis()));

		return Jwts.builder()
				.setIssuer("token Jwt")
				.setSubject(sub)
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}catch (Exception e) {
			return false;
		}
	}	
	
	public Long getIdUsuario(String token) {
		Gson gson = new Gson();
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		SubjectDTO sub = gson.fromJson(claims.getSubject(), SubjectDTO.class);
		return Long.parseLong(sub.getId());
	}
	
	public String getTokenRequest(HttpServletRequest request){
		return request.getHeader("Authorization").substring(7, request.getHeader("Authorization").length());
	}
	
	public Long getIdPerTokenOnRequest(HttpServletRequest request) {
		return getIdUsuario(getTokenRequest(request));
	}

}
