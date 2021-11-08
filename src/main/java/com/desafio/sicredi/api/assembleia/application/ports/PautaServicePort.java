package com.desafio.sicredi.api.assembleia.application.ports;

import java.util.List;
import java.util.Optional;

import com.desafio.sicredi.core.domain.Pauta;

public interface PautaServicePort {

	Pauta save(Pauta pauta);
	List<Pauta> findAll();
	Optional<Pauta> findById(Long id);

}
