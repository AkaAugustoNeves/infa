package br.com.pilares.infaauthorizationserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pilares.infaauthorizationserver.model.Perfil;

@RestController
@RequestMapping(value = "/teste")
public class HelloController {
	
	@GetMapping
	public String teste(@RequestHeader("id") String id, @RequestHeader("role") String role) {
		System.out.println("id: "+id);
		return role;
	}

}
