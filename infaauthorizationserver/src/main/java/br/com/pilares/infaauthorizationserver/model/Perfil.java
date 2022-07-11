package br.com.pilares.infaauthorizationserver.model;

import org.springframework.security.core.GrantedAuthority;

public class Perfil implements GrantedAuthority{
	
	private String nome;
	
	public Perfil(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String getAuthority() {
		return this.nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
