package com.desafio.sicredi.api.votacao.application.ports;

import java.util.List;
import java.util.Optional;

import com.desafio.sicredi.core.domain.Votacao;

public interface VotacaoRepositoryPort {

	Votacao votar(Votacao votacao);
	List<Votacao> findBySessao(Long idSessao);
	Optional<Votacao> findByAssociadoAndSessao(Long idAssociado, Long idSessao);

}
