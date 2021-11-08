package com.desafio.sicredi.core.exception;

public class PautaNaoCadastradoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public PautaNaoCadastradoException() {
		super("a Pauta informada não está cadastrada");
	}

}
