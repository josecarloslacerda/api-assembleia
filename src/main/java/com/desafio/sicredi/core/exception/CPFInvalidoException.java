package com.desafio.sicredi.core.exception;

public class CPFInvalidoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public CPFInvalidoException() {
		super("O CPF informado é inválido");
	}

}
