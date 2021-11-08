package com.desafio.sicredi.core.exception;

public class CPFInformadoJaCadastradoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public CPFInformadoJaCadastradoException() {
		super("Já existe um Associado cadastrado com este CPF");
	}

}
