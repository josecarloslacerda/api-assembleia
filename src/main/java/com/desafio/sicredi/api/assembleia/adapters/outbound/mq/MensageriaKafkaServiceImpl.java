package com.desafio.sicredi.api.assembleia.adapters.outbound.mq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.desafio.sicredi.api.assembleia.application.ports.MensageriaServicePort;
import com.desafio.sicredi.api.votacao.adapters.dto.response.VotosContabilizadoDto;
import com.desafio.sicredi.api.votacao.application.ports.VotacaoServicePort;
import com.desafio.sicredi.core.domain.Sessao;
import com.desafio.sicredi.core.exception.ConversaoObjetoJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Primary
@Service
public class MensageriaKafkaServiceImpl implements MensageriaServicePort {

	@Value("${topic.name.producer}")
    private String topicName;

    private KafkaTemplate<String, String> kafkaTemplate;

    private VotacaoServicePort votacaoService;

    public MensageriaKafkaServiceImpl(final VotacaoServicePort votacaoService,
    		final KafkaTemplate<String, String> kafkaTemplate) {
		this.votacaoService = votacaoService;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void notificar(Object objeto) {
		Sessao sessao = (Sessao) objeto;

		VotosContabilizadoDto votos = votacaoService.contabilizarVotacao(sessao.getId());

		ObjectMapper mapper = new ObjectMapper();

		try {
			String json = mapper.writeValueAsString(votos);
			kafkaTemplate.send(topicName, json);
		} catch (JsonProcessingException e) {
			throw new ConversaoObjetoJsonException();
		}
	}

}
