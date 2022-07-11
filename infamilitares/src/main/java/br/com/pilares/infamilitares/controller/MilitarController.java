package br.com.pilares.infamilitares.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pilares.infamilitares.model.DTO.MilitarDTO;
import br.com.pilares.infamilitares.model.entity.Militar;
import br.com.pilares.infamilitares.service.MilitarService;

@RestController
@RequestMapping(value = "/militar")
public class MilitarController {

	@Autowired
	private MilitarService militarService;
	
	@GetMapping(value = "/search/telefone")
	public ResponseEntity<MilitarDTO> getMilitarPorTelefone(@RequestParam String telefone) {
		return militarService.getMilitarPorTelefone(telefone);
	}
	
	@GetMapping(value = "/search/id")
	public ResponseEntity<MilitarDTO> getMilitarPorId(@RequestParam Long id) {
		return militarService.getById(id);
	}
	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> activeUser(@PathVariable Long id){
		return militarService.activeUser(id);
	}
	
}
