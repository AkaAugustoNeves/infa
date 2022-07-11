package br.com.pilares.infamilitares.model.DTO;

import java.util.List;

import br.com.pilares.infamilitares.model.entity.Militar;
import br.com.pilares.infamilitares.service.MilitarService;

public class MilitarDTO {
	
	private Long id;
	private String telefone;
	private String senha;
	private boolean isAtivo;
	private PatenteDTO patente;
	private SecaoDTO secao;

	public MilitarDTO(Militar militar) {
		this.id = militar.getId();
		this.telefone = militar.getTelefone();
		this.senha = militar.getSenha();
		this.isAtivo = militar.isAtivo();
		this.patente = new PatenteDTO(militar.getPatente());
		this.secao = new SecaoDTO(militar.getSecao());		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	public PatenteDTO getPatente() {
		return patente;
	}

	public void setPatente(PatenteDTO patente) {
		this.patente = patente;
	}

	public SecaoDTO getSecao() {
		return secao;
	}

	public void setSecao(SecaoDTO secao) {
		this.secao = secao;
	}
	
	
	
}
