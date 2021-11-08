package com.desafio.sicredi.api.assembleia.adapters.dto.response;

import com.desafio.sicredi.core.patterns.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Sessao", description="Objeto relacionado às informações da Pauta")
public class SessaoRetornoDto extends DTO {

	@ApiModelProperty(value = "Código de identificação da Sessão",
			name = "id",
			example = "1")
	private Long id;

	@ApiModelProperty(value = "Data e Horada abertura da Sessão",
			name = "dataHoraAbertura")
	private String dataHoraAbertura;

	@ApiModelProperty(value = "Duração da Sessão em minutos",
			name = "duracao")
	private Integer duracao;

	@ApiModelProperty(value = "Código de identificação da Pauta",
			name = "id",
			example = "1")
	private Long pauta;

	public SessaoRetornoDto() {
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDataHoraAbertura() {
		return dataHoraAbertura;
	}

	public void setDataHoraAbertura(String dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}

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
