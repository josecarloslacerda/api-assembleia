package com.desafio.sicredi.api.associado.application.ports;

import java.util.List;
import java.util.Optional;

import com.desafio.sicredi.core.domain.Associado;

public interface AssociadoServicePort {

	Associado save(Associado associado);
	List<Associado> findAll();
	Optional<Associado> findById(Long id);
	Optional<Associado> findByCPF(String cpf);
}
