package com.desafio.sicredi.core.exception;

public class AssociadoNaoAptoAssembleiaException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public AssociadoNaoAptoAssembleiaException() {
		super("O CPF do Associado informado Não está apto a participar de Assembléias e portanto não será cadastrado");
	}

}
