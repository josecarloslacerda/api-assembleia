package com.desafio.sicredi.api.votacao.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.sicredi.api.assembleia.application.ports.SessaoServicePort;
import com.desafio.sicredi.api.associado.application.ports.AssociadoServicePort;
import com.desafio.sicredi.api.votacao.adapters.dto.response.VotosContabilizadoDto;
import com.desafio.sicredi.api.votacao.application.ports.VotacaoRepositoryPort;
import com.desafio.sicredi.api.votacao.application.ports.VotacaoServicePort;
import com.desafio.sicredi.core.domain.Associado;
import com.desafio.sicredi.core.domain.Sessao;
import com.desafio.sicredi.core.domain.Votacao;
import com.desafio.sicredi.core.domain.enuns.OpcaoVotacaoEnum;
import com.desafio.sicredi.core.exception.AssociadoJaVotouNaSessaoException;
import com.desafio.sicredi.core.exception.AssociadoNaoCadastradoException;
import com.desafio.sicredi.core.exception.NaoHaVotacaoNaSessaoException;
import com.desafio.sicredi.core.exception.SessaoAbertaException;
import com.desafio.sicredi.core.exception.SessaoFechacaException;

@Service
public class VotacaoServiceImpl implements VotacaoServicePort {

	private VotacaoRepositoryPort votacaoRepository;
	private SessaoServicePort sessaoService;
	private AssociadoServicePort associadoService;

	@Autowired
	private VotacaoServiceImpl votacaoService;

	public VotacaoServiceImpl(final VotacaoRepositoryPort votacaoRepository,
			final SessaoServicePort sessaoService,
			final AssociadoServicePort associadoService) {
		this.votacaoRepository = votacaoRepository;
		this.sessaoService = sessaoService;
		this.associadoService = associadoService;
	}

	@Override
	@Transactional
	public Votacao votar(Votacao votacao) {

		if (!verificaSeSessaoEstaAbertaParaVotacao(votacao.getSessao().getId())) {
			throw new SessaoFechacaException();
		};

		votacao.setAssociado(obterAssociadoCadastrado(votacao.getAssociado().getCpf()));
		verificaSeAssociadoJaVotouNaSessao(votacao.getAssociado().getId(), votacao.getSessao().getId());

		return votacaoRepository.votar(votacao);
	}

	@Override
	public List<Votacao> findBySessao(Long idSessao) {
		List<Votacao> listaVotacao = votacaoRepository.findBySessao(idSessao);

		if (listaVotacao == null || listaVotacao.isEmpty()) {
			throw new NaoHaVotacaoNaSessaoException();
		}
		return listaVotacao;
	}

	@Override
	public VotosContabilizadoDto contabilizarVotacao(Long idSessao) {

		if (verificaSeSessaoEstaAbertaParaVotacao(idSessao)) {
			throw new SessaoAbertaException();
		};

		List<Votacao> listaVotacao = votacaoService.findBySessao(idSessao);

		VotosContabilizadoDto votosContabilizados = new VotosContabilizadoDto();
		if (listaVotacao != null && !listaVotacao.isEmpty()) {

			votosContabilizados.setTotalVotos(listaVotacao.size());
			votosContabilizados.setVotosAFavor(listaVotacao.stream()
					.filter(v -> v.getVoto().equals(OpcaoVotacaoEnum.SIM)).count());
			votosContabilizados.setVotosContra(listaVotacao.stream()
					.filter(v -> v.getVoto().equals(OpcaoVotacaoEnum.NAO)).count());

			votosContabilizados.setIdSessao(idSessao);

			Optional<Sessao> sessao = sessaoService.findById(idSessao);
			votosContabilizados.setDescricaoPauta(sessao.get().getPauta().getDescricao());
		}
		return votosContabilizados;
	}

	private Boolean verificaSeSessaoEstaAbertaParaVotacao(Long idSessao) {
		return sessaoService.sessaoAbertaParaVotacao(idSessao);
	}

	private Associado obterAssociadoCadastrado(String cpf) {
		Optional<Associado> associado = associadoService.findByCPF(cpf);
		if (!associado.isPresent()) {
			throw new AssociadoNaoCadastradoException();
		}
		return associado.get();
	}

	private void verificaSeAssociadoJaVotouNaSessao(Long idAssociado, Long idSessao) {
		Optional<Votacao> votacao = votacaoRepository.findByAssociadoAndSessao(idAssociado, idSessao);
		if (votacao.isPresent()) {
			throw new AssociadoJaVotouNaSessaoException();
		}
	}

}
