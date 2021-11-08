package com.desafio.sicredi.core.exception;

public class SessaoFechacaException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public SessaoFechacaException() {
		super("A Sessão já expirou o prazo de votação");
	}

}
