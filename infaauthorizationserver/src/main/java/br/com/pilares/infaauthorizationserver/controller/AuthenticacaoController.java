package br.com.pilares.infaauthorizationserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pilares.infaauthorizationserver.feignclients.MilitarFeignClient;
import br.com.pilares.infaauthorizationserver.model.dto.MilitarDTO;
import br.com.pilares.infaauthorizationserver.model.dto.TokenDTO;
import br.com.pilares.infaauthorizationserver.model.form.LoginForm;
import br.com.pilares.infaauthorizationserver.service.AutenticacaoService;

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
	public String ativarconta() {
		return "ativarConta";
	}
	
	@PostMapping("/create-account")
	public String criarconta() {
		return "create";
	}

}
