package com.desafio.sicredi.core.exception;

public class SessaoNaoCadastradaException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public SessaoNaoCadastradaException() {
		super("A Sessão informada não está cadastrada");
	}

}
