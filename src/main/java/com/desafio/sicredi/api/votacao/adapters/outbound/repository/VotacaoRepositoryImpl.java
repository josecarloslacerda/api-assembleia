package com.desafio.sicredi.api.votacao.adapters.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.desafio.sicredi.api.votacao.application.ports.VotacaoRepositoryPort;
import com.desafio.sicredi.core.domain.Votacao;

@Component
public class VotacaoRepositoryImpl implements VotacaoRepositoryPort {

	private VotacaoRepositorySpringData votacaoRepository;

	public VotacaoRepositoryImpl(final VotacaoRepositorySpringData votacaoRepository) {
		this.votacaoRepository = votacaoRepository;
	}

	@Override
	public Votacao votar(Votacao votacao) {
		return votacaoRepository.save(votacao);
	}

	@Override
	public List<Votacao> findBySessao(Long idSessao) {
		return votacaoRepository.findBySessao(idSessao);
	}

	@Override
	public Optional<Votacao> findByAssociadoAndSessao(Long idAssociado, Long idSessao) {
		return votacaoRepository.findByAssociadoAndSessao(idAssociado, idSessao);
	}

}
