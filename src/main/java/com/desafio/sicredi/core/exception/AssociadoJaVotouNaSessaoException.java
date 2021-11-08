package com.desafio.sicredi.core.exception;

public class AssociadoJaVotouNaSessaoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public AssociadoJaVotouNaSessaoException() {
		super("O Associado informado já votou nesta Sessão");
	}

}
