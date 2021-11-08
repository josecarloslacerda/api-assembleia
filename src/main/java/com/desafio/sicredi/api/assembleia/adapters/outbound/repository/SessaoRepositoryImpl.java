package com.desafio.sicredi.api.assembleia.adapters.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.desafio.sicredi.api.assembleia.application.ports.SessaoRepositoryPort;
import com.desafio.sicredi.core.domain.Sessao;

@Component
public class SessaoRepositoryImpl implements SessaoRepositoryPort {

	private SessaoRepositorySpringData sessaoRepository;

	public SessaoRepositoryImpl(SessaoRepositorySpringData sessaoRepository) {
		this.sessaoRepository = sessaoRepository;
	}

	@Override
	public Sessao save(Sessao sessao) {
		return sessaoRepository.save(sessao);
	}

	@Override
	public List<Sessao> findAll() {
		return sessaoRepository.findAll();
	}

	@Override
	public Optional<Sessao> findById(Long id) {
		return sessaoRepository.findById(id);
	}

	@Override
	public Optional<Sessao> findByIdPauta(Long idPauta) {
		return sessaoRepository.findByIdPauta(idPauta);
	}



}
