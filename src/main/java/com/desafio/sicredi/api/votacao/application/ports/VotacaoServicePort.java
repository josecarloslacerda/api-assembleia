package com.desafio.sicredi.api.votacao.application.ports;

import java.util.List;

import com.desafio.sicredi.api.votacao.adapters.dto.response.VotosContabilizadoDto;
import com.desafio.sicredi.core.domain.Votacao;

public interface VotacaoServicePort {

	Votacao votar(Votacao votacao);
	List<Votacao> findBySessao(Long idSessao);
	VotosContabilizadoDto contabilizarVotacao(Long idSessao);

}
