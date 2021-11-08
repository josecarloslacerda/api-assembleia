package com.desafio.sicredi.core.domain.enuns;

public enum OpcaoVotacaoEnum {

	SIM,
	NAO;

	public OpcaoVotacaoEnum getOpcao(String opcao) {

		if (opcao == null) {
			//TODO incluir Exceção
		}

		if (opcao.toUpperCase().equals("SIM")){
			return OpcaoVotacaoEnum.SIM;
		}

		return OpcaoVotacaoEnum.NAO;
	}
}
