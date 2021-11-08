package com.desafio.sicredi.api.associado.adapters.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.desafio.sicredi.api.associado.application.ports.AssociadoRepositoryPort;
import com.desafio.sicredi.core.domain.Associado;

@Component
public class AssociadoRepositoryImpl implements AssociadoRepositoryPort {

	private AssociadoRepositorySpringData associadoRepository;

	public AssociadoRepositoryImpl(AssociadoRepositorySpringData AssociadoRepository) {
		this.associadoRepository = AssociadoRepository;
	}

	@Override
	public Associado save(Associado associado) {
		return associadoRepository.save(associado);
	}

	@Override
	public List<Associado> findAll() {
		return associadoRepository.findAll();
	}

	@Override
	public Optional<Associado> findById(Long id) {
		return associadoRepository.findById(id);
	}

	@Override
	public Optional<Associado> findByCPF(String cpf) {
		return associadoRepository.findByCpf(cpf);
	}

}
