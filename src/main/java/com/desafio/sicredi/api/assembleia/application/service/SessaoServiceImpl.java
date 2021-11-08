package com.desafio.sicredi.api.assembleia.application.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.sicredi.api.assembleia.application.ports.AgendadorServicePort;
import com.desafio.sicredi.api.assembleia.application.ports.SessaoRepositoryPort;
import com.desafio.sicredi.api.assembleia.application.ports.SessaoServicePort;
import com.desafio.sicredi.core.domain.Pauta;
import com.desafio.sicredi.core.domain.Sessao;
import com.desafio.sicredi.core.exception.PautaJaContemSessaoException;
import com.desafio.sicredi.core.exception.PautaNaoCadastradoException;
import com.desafio.sicredi.core.exception.SessaoNaoCadastradaException;

@Service
public class SessaoServiceImpl implements SessaoServicePort {

	private SessaoRepositoryPort sessaoRepository;
	private PautaServiceImpl pautaService;

	@Autowired
	private SessaoServicePort sessaoService;

	public SessaoServiceImpl(final SessaoRepositoryPort sessaoRepository,
			final PautaServiceImpl pautaService) {
		this.sessaoRepository = sessaoRepository;
		this.pautaService = pautaService;

	}

	@Override
	public Sessao abrirSessao(Sessao sessao) {

		verificaSeHaPautaCadastrada(sessao.getPauta().getId());
		verificaSeHaSessaoCadastradaParaPauta(sessao.getPauta());

		if (sessao.getDuracao() <= 0) {
			sessao.setDuracao(1);
		}

		sessao.setDataHoraAbertura(new Date());

		Sessao sessaoIncluida = sessaoRepository.save(sessao);

		return sessaoIncluida;
	}

	@Override
	public List<Sessao> findAll() {
		return sessaoRepository.findAll();
	}

	@Override
	public Optional<Sessao> findById(Long id) {
		Optional<Sessao> sessao = sessaoRepository.findById(id);
		return sessao;
	}

	@Override
	public Boolean sessaoAbertaParaVotacao(Long id) {

		Optional<Sessao> sessao = sessaoRepository.findById(id);

		if (!sessao.isPresent()) {
			throw new SessaoNaoCadastradaException();
		}

		LocalDateTime dataHoraVotacao = Instant.ofEpochMilli(sessao.get().getDataHoraAbertura().getTime())
				.atZone(ZoneId.systemDefault()).toLocalDateTime();
		dataHoraVotacao = dataHoraVotacao.plusMinutes(sessao.get().getDuracao());

		return LocalDateTime.now().isBefore(dataHoraVotacao);
	}

	@Override
	public List<Sessao> findSessoesAbertas() {
		List<Sessao> sessoes = sessaoService.findAll();

		return sessoes.stream().filter(sessao -> {
			LocalDateTime dataHoraVotacao = Instant.ofEpochMilli(sessao.getDataHoraAbertura().getTime())
					.atZone(ZoneId.systemDefault()).toLocalDateTime();
			dataHoraVotacao = dataHoraVotacao.plusMinutes(sessao.getDuracao());
			return LocalDateTime.now().isBefore(dataHoraVotacao);
		}).collect(Collectors.toList());

	}

	private void verificaSeHaSessaoCadastradaParaPauta(Pauta pauta) {

		Optional<Sessao> sessao = sessaoRepository.findByIdPauta(pauta.getId());

		if (sessao.isPresent()) {
			throw new PautaJaContemSessaoException();
		}

	}

	private void verificaSeHaPautaCadastrada(Long idPauta) {
		Optional<Pauta> pauta = pautaService.findById(idPauta);

		if (!pauta.isPresent()) {
			throw new PautaNaoCadastradoException();
		}

	}

}
