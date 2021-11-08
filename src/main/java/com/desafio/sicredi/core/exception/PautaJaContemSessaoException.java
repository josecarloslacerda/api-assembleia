package com.desafio.sicredi.core.exception;

public class PautaJaContemSessaoException extends ExcecaoGenerica {

	private static final long serialVersionUID = 1L;

	public PautaJaContemSessaoException() {
		super("Para a Pauta infomada já existe uma Sessão cadastrada. Só pode existir uma Sessão por Pauta");
	}

}
