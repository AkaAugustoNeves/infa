package br.com.pilares.infaauthorizationserver.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.pilares.infaauthorizationserver.model.dto.MilitarDTO;

@Component
@FeignClient(name = "infa-militares", path = "/infamilitar/militar")
public interface MilitarFeignClient {
	
	@GetMapping(value = "/search/telefone")
	public ResponseEntity<MilitarDTO> getMilitarPorTelefone(@RequestParam String telefone);
	
	@GetMapping(value = "/search/id")
	public ResponseEntity<MilitarDTO> getById(@RequestParam Long id);
}
