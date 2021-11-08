package com.desafio.sicredi.api.assembleia.application.ports;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.desafio.sicredi.core.domain.Sessao;

public interface SessaoRepositoryPort {

	Sessao save(Sessao sessao);
	List<Sessao> findAll();
	Optional<Sessao> findById(Long id);
	Optional<Sessao> findByIdPauta(@Param("id") Long idPauta);

}
