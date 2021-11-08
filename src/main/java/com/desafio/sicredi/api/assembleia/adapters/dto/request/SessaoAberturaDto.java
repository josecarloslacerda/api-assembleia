package com.desafio.sicredi.api.assembleia.adapters.dto.request;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;

public class SessaoAberturaDto {

	@ApiModelProperty(value = "Duração, em minutos, que a sessão deve ficar aberta para votação. "
			+ "Caso não seja informado, será considerado 1 minuto de duração",
			name = "duracao",
			example = "1")
	private Integer duracao;

	@ApiModelProperty(value = "Códigode identificação da Pauta",
			name = "idPauta",
			allowEmptyValue = false,
			required = true,
			example = "1")
	@NotEmpty(message="O código de identificação da Pauta deve ser informada")
	private Long pauta;

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Long getPauta() {
		return pauta;
	}

	public void setPauta(Long pauta) {
		this.pauta = pauta;
	}

}
