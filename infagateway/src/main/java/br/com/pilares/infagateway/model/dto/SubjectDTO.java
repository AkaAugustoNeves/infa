package br.com.pilares.infagateway.model.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.pilares.infagateway.model.Perfil;

public class SubjectDTO {
	
	private String id;
	private List<String> perfis = new ArrayList<>();
	
	public SubjectDTO(Long id, List<Perfil> perfis) {
		this.id = id.toString();
		for (Perfil perfil: perfis) {
			this.perfis.add(perfil.getNome());
		}
	}
	
	public SubjectDTO(String id, List<Perfil> perfis) {
		this.id = id;
		for (Perfil perfil: perfis) {
			this.perfis.add(perfil.getNome());
		}
	}
	
	public SubjectDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<String> perfis) {
		this.perfis = perfis;
	}	

}