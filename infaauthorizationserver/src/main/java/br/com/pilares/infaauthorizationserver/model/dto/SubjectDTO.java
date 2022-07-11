package br.com.pilares.infaauthorizationserver.model.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.pilares.infaauthorizationserver.model.Perfil;

public class SubjectDTO {
	
	private String id;
	private List<String> perfis = new ArrayList<>();
	
	public SubjectDTO(Long id, List<Perfil> perfis) {
		this.id = id.toString();
		for (Perfil perfil: perfis) {
			this.perfis.add(perfil.getNome());
		}
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
	
	@Override
	public String toString() {
		String r = "id: "+this.id;
		return r;
	}
	
	

}
