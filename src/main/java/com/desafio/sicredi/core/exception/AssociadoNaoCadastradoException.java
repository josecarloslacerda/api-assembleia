package com.desafio.sicredi.core.exception;

public class AssociadoNaoCadastradoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public AssociadoNaoCadastradoException() {
		super("O Associado informado não está cadastrado");
	}

}
