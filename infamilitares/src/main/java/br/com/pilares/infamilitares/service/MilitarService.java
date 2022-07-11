package br.com.pilares.infamilitares.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pilares.infamilitares.model.DTO.MilitarDTO;
import br.com.pilares.infamilitares.model.entity.Militar;
import br.com.pilares.infamilitares.model.entity.Patente;
import br.com.pilares.infamilitares.model.entity.Secao;
import br.com.pilares.infamilitares.repository.MilitarRepository;

@Service
public class MilitarService {
	
	@Autowired
	private MilitarRepository militarRepository;
	
	public ResponseEntity<MilitarDTO> getMilitarPorTelefone(String telefone) {
		Optional<Militar> militar = militarRepository.findByTelefone(telefone);
		if(militar.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(new MilitarDTO(militar.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	} 
	
	public ResponseEntity<Militar> activeUser(Long id) {
		Militar militar = militarRepository.findById(id).get();
		militar.setAtivo(true);
		return ResponseEntity.ok().body(militarRepository.save(militar));
	}
	
	public ResponseEntity<MilitarDTO> getById(Long id) {
		Optional<Militar> militar = militarRepository.findById(id);
		if(militar.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(new MilitarDTO(militar.get()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	/*public static List<String> converterPerfis(Secao secao, Patente patente) {
		List<String> perfis = new ArrayList<>();
		perfis.add(convertSecaoPerfil(secao.getNome()));
		perfis.add(convertPatentPerfil(patente.getNome()));
		return perfis;
	}private static String convertPatentPerfil(String patenteNome) {
		return "P_"+patenteNome.toUpperCase();
	}
	
	private static String convertSecaoPerfil(String secaoNome) {
		return "S_"+secaoNome.toUpperCase();
	}*/


}
