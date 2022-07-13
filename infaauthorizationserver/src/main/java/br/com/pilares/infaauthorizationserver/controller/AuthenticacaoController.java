package br.com.pilares.infaauthorizationserver.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.pilares.infaauthorizationserver.feignclients.MilitarFeignClient;
import br.com.pilares.infaauthorizationserver.model.dto.MilitarDTO;
import br.com.pilares.infaauthorizationserver.model.dto.SubjectDTO;
import br.com.pilares.infaauthorizationserver.model.dto.TokenDTO;
import br.com.pilares.infaauthorizationserver.model.form.LoginForm;
import br.com.pilares.infaauthorizationserver.service.AutenticacaoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticacaoController {
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> findByEmail(@RequestBody LoginForm form) {
		return autenticacaoService.login(form);
	}
	
	@PostMapping("/activy-account")
	public SubjectDTO ativarconta(@RequestHeader("Authorization") String token) {
		String secret = "InF4Br4$Il";
		String json = Jwts.parser().setSigningKey(secret).parseClaimsJws(token.substring(7, token.length())).getBody().getSubject();
		Gson g = new Gson();
		SubjectDTO sub = g.fromJson(json, SubjectDTO.class);
		return sub;
		
		//return Jwts.parser().setSigningKey(secret).parseClaimsJws(token.substring(7, token.length())).getBody().getSubject();
		//return !(Jwts.parser().setSigningKey(secret).parseClaimsJws(token.substring(7, token.length())).getBody().getExpiration().getTime() > new Date().getTime());
		//return String.valueOf(sub.getPerfis());
		//return !(Jwts.parser().setSigningKey(secret).parseClaimsJws(token.substring(7, token.length())).getBody().getExpiration().getTime() > new Date().getTime());
	}
	
	@PostMapping("/create-account")
	public String criarconta() {
		return "create";
	}

}
