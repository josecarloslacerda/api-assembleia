package com.desafio.sicredi.core.patterns;

import io.swagger.annotations.ApiModelProperty;

public class Response <D> {

	@ApiModelProperty(value = "Código de resposta HTTP",
			name = "statusCode")
	private int statusCode;

	@ApiModelProperty(value = "Mensagem de retorno da operação",
			name = "mensagem")
	private String mensagem;

	@ApiModelProperty(value = "Objeto de retorno, caso tenha",
			name = "data")
	private D data;

	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public D getData() {
		return data;
	}
	public void setData(D data) {
		this.data = data;
	}

	public static class Factory{
		private Factory() {}

		public static <T> Response<T> criar(T dto){
			Response<T> response = new Response<T>();
			response.setData(dto);
			return response;
		}

		public static <T> Response<T> criar(){
			Response<T> response = new Response<T>();
			return response;
		}
	}
}
