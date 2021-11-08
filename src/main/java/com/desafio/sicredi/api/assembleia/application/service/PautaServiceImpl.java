package com.desafio.sicredi.api.assembleia.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.desafio.sicredi.api.assembleia.application.ports.PautaRepositoryPort;
import com.desafio.sicredi.api.assembleia.application.ports.PautaServicePort;
import com.desafio.sicredi.core.domain.Pauta;

@Service
public class PautaServiceImpl implements PautaServicePort {

	private PautaRepositoryPort pautaRepository;

	public PautaServiceImpl(final PautaRepositoryPort pautaRepository) {
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
