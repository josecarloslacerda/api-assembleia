package com.desafio.sicredi.core.exception;

public class NaoHaVotacaoNaSessaoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public NaoHaVotacaoNaSessaoException() {
		super("Não há votações nesta Sessão");
	}

}
