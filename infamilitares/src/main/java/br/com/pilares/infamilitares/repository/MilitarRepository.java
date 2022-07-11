package br.com.pilares.infamilitares.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pilares.infamilitares.model.entity.Militar;

public interface MilitarRepository extends JpaRepository<Militar, Long> {
	
	Optional<Militar> findByTelefone(String telefone);

}
