package com.desafio.sicredi.api.assembleia.adapters.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.desafio.sicredi.api.assembleia.application.ports.PautaRepositoryPort;
import com.desafio.sicredi.core.domain.Pauta;

@Component
public class PautaRepositoryImpl implements PautaRepositoryPort {

	private PautaRepositorySpringData pautaRepository;

	public PautaRepositoryImpl(PautaRepositorySpringData pautaRepository) {
		this.pautaRepository = pautaRepository;
	}

	@Override
	public Pauta save(Pauta pauta) {
		return pautaRepository.save(pauta);
	}

	@Override
	public List<Pauta> findAll() {
		return pautaRepository.findAll();
	}

	@Override
	public Optional<Pauta> findById(Long id) {
		return pautaRepository.findById(id);
	}

}
