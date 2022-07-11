package br.com.pilares.infamilitares.model.DTO;

import br.com.pilares.infamilitares.model.entity.Secao;

public class SecaoDTO {
	

	private String nome;

	public SecaoDTO(Secao secao) {
		this.nome = secao.getNome();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
