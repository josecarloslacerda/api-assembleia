package com.desafio.sicredi.api.assembleia.application.ports;

import java.util.List;
import java.util.Optional;

import com.desafio.sicredi.core.domain.Sessao;

public interface SessaoServicePort {

	Sessao abrirSessao(Sessao sessao);
	List<Sessao> findAll();
	List<Sessao> findSessoesAbertas();
	Optional<Sessao> findById(Long id);
	Boolean sessaoAbertaParaVotacao(Long id);
}
