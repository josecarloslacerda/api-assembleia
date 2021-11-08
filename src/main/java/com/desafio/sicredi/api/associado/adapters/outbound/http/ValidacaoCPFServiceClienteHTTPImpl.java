package com.desafio.sicredi.api.associado.adapters.outbound.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.desafio.sicredi.api.associado.adapters.dto.response.ValidacaoCpfRetornoDto;
import com.desafio.sicredi.api.associado.application.ports.ValidacaoCPFServicePort;
import com.desafio.sicredi.core.exception.CPFInvalidoException;

@Component
public class ValidacaoCPFServiceClienteHTTPImpl implements ValidacaoCPFServicePort{

	private final String URL = "https://user-info.herokuapp.com/users/{cpf}";

	public Boolean validaCPF(String cpf) {
		RestTemplate restTemplate = new RestTemplate();

		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("cpf", cpf);

		try {
			ValidacaoCpfRetornoDto retorno = restTemplate.getForObject(URL, ValidacaoCpfRetornoDto.class, parametros);

			if (retorno != null && retorno.getStatus().toUpperCase().equals("ABLE_TO_VOTE")) {
				return true;
			}

		} catch (Exception ex) {
			throw new CPFInvalidoException();
		}

		return false;
	}

}
