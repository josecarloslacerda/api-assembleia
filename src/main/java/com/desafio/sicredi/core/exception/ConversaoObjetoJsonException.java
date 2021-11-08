package com.desafio.sicredi.core.exception;

public class ConversaoObjetoJsonException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public ConversaoObjetoJsonException() {
		super("Não foi possível converter o objeto informado em Json");
	}

}
