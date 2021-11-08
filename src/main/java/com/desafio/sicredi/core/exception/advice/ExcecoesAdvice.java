package com.desafio.sicredi.core.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desafio.sicredi.core.exception.AssociadoJaVotouNaSessaoException;
import com.desafio.sicredi.core.exception.AssociadoNaoAptoAssembleiaException;
import com.desafio.sicredi.core.exception.AssociadoNaoCadastradoException;
import com.desafio.sicredi.core.exception.CPFInformadoJaCadastradoException;
import com.desafio.sicredi.core.exception.CPFInvalidoException;
import com.desafio.sicredi.core.exception.CPFMalFormatadoException;
import com.desafio.sicredi.core.exception.ConversaoObjetoJsonException;
import com.desafio.sicredi.core.exception.ConversaoObjetoJsonException;
import com.desafio.sicredi.core.exception.ExcecaoGenerica;
import com.desafio.sicredi.core.exception.NaoHaVotacaoNaSessaoException;
import com.desafio.sicredi.core.exception.PautaJaContemSessaoException;
import com.desafio.sicredi.core.exception.PautaNaoCadastradoException;
import com.desafio.sicredi.core.exception.SessaoAbertaException;
import com.desafio.sicredi.core.exception.SessaoFechacaException;
import com.desafio.sicredi.core.exception.SessaoNaoCadastradaException;
import com.desafio.sicredi.core.patterns.DTO;
import com.desafio.sicredi.core.patterns.Response;

@ControllerAdvice
public class ExcecoesAdvice {

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> excecaoNaoTratada(Exception ex){
		Response<DTO> response = Response.Factory.criar();
		response.setMensagem("Exceção: Erro não tratado pela API. Uma exceção genérica ocorreu: " + ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	@ResponseBody
	@ExceptionHandler(value = {AssociadoJaVotouNaSessaoException.class,
			AssociadoNaoCadastradoException.class,
			NaoHaVotacaoNaSessaoException.class,
			PautaJaContemSessaoException.class,
			PautaNaoCadastradoException.class,
			SessaoAbertaException.class,
			SessaoFechacaException.class,
			SessaoNaoCadastradaException.class,
			AssociadoNaoAptoAssembleiaException.class,
			ConversaoObjetoJsonException.class,
			CPFInvalidoException.class,
			CPFInformadoJaCadastradoException.class,
			CPFMalFormatadoException.class})
	public ResponseEntity<Response<DTO>> tratamentoExcecoes(ExcecaoGenerica ex){
		Response<DTO> response = Response.Factory.criar();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setMensagem("Exceção: " + ex.getMensagem());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
