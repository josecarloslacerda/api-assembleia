package com.desafio.sicredi.core.exception;

public class CPFMalFormatadoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public CPFMalFormatadoException() {
		super("O CPF informado está mal formatado. Só deve conter dígitos e seu tamanho deve conter 11 caracteres");
	}

}
