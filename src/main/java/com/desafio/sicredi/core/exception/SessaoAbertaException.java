package com.desafio.sicredi.core.exception;

public class SessaoAbertaException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public SessaoAbertaException() {
		super("A Sessão ainda está aberta e por isso não é permitido contabilizar os votos");
	}

}
