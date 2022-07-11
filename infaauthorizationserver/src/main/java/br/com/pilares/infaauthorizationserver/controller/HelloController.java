package br.com.pilares.infaauthorizationserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class HelloController {
	
	@GetMapping
	public String teste(/*@RequestHeader("id") String id*/) {
		System.out.println("teste");
		return "id";
	}

}
