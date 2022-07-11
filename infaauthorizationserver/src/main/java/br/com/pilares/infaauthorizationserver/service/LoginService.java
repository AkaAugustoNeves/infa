package br.com.pilares.infaauthorizationserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pilares.infaauthorizationserver.feignclients.MilitarFeignClient;
import br.com.pilares.infaauthorizationserver.model.dto.MilitarDTO;

@Service
public class LoginService implements UserDetailsService{
	
	@Autowired
	private MilitarFeignClient militarFeignClient;
	
	@Override
	public UserDetails loadUserByUsername(String telefone) throws UsernameNotFoundException {
		ResponseEntity<MilitarDTO> usuario = militarFeignClient.getMilitarPorTelefone(telefone);
		if (usuario.hasBody()) {
			return usuario.getBody();
		}
		return (UserDetails) new UsernameNotFoundException("Dados Invalidos");
	}

}
