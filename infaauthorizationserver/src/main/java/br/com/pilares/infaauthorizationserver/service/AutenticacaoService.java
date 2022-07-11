package br.com.pilares.infaauthorizationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.pilares.infaauthorizationserver.feignclients.MilitarFeignClient;
import br.com.pilares.infaauthorizationserver.model.dto.TokenDTO;
import br.com.pilares.infaauthorizationserver.model.form.LoginForm;

@Service
public class AutenticacaoService {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private MilitarFeignClient militarFeignClient;
	
	@Autowired
	private TokenService tokenService;

	public ResponseEntity<TokenDTO> login(LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
