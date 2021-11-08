package com.desafio.sicredi.api.assembleia.adapters.outbound.mq;

import org.springframework.stereotype.Service;

import com.desafio.sicredi.api.assembleia.application.ports.MensageriaServicePort;
import com.desafio.sicredi.core.domain.Sessao;

@Service
public class MensageriaMockServiceImpl implements MensageriaServicePort {

	@Override
	public void notificar(Object objeto) {
		Sessao sessao = (Sessao) objeto;
		System.out.println("Notificação feita - id da sessão: " + sessao.getId());
	}

}
