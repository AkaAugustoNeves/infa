package br.com.pilares.infamilitares.model.DTO;

import br.com.pilares.infamilitares.model.entity.Patente;

public class PatenteDTO {
	
	private String nome;

	public PatenteDTO(Patente patente) {
		this.nome = patente.getNome();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
