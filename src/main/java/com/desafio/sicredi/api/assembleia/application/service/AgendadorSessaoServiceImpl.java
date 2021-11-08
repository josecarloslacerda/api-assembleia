package com.desafio.sicredi.api.assembleia.application.service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.sicredi.api.assembleia.application.ports.AgendadorServicePort;
import com.desafio.sicredi.api.assembleia.application.ports.MensageriaServicePort;
import com.desafio.sicredi.api.assembleia.application.ports.SessaoServicePort;
import com.desafio.sicredi.core.domain.Sessao;

@Service
public class AgendadorSessaoServiceImpl implements AgendadorServicePort{

	private MensageriaServicePort mensageriaService;
	private SessaoServicePort sessaoService;

	@Autowired
	private AgendadorServicePort agendadorService;

	public AgendadorSessaoServiceImpl(final MensageriaServicePort mensageriaService,
			final SessaoServicePort sessaoService) {
		this.mensageriaService = mensageriaService;
		this.sessaoService = sessaoService;
	}

	@Override
	public void agendarTarefa(Object objeto) {
		Sessao sessao = (Sessao) objeto;
		Instant instante = sessao.getDataHoraAbertura().toInstant();
		instante = instante.plusSeconds(sessao.getDuracao() * 60);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				mensageriaService.notificar(sessao);
			}
		}, Date.from(instante));
	}

	@Override
	public void agendarTarefa() {
		agendarNotificacaoSessoesAbertas();
	}

	public void agendarNotificacaoSessoesAbertas() {
		List<Sessao> sessoes = sessaoService.findSessoesAbertas();
		sessoes.forEach(sessao -> agendadorService.agendarTarefa(sessao));
	}

}
