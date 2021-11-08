package com.desafio.sicredi.api.assembleia.adapters.dto.request;

import javax.validation.constraints.NotEmpty;

import com.desafio.sicredi.core.patterns.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Pauta", description="Objeto relacionado às informações da Pauta")
public class PautaInclusaoDto extends DTO {

	@ApiModelProperty(value = "Descrição da Pauta",
			name = "nome",
			allowEmptyValue = false,
			required = true,
			example = "texto da Pauta")
	@NotEmpty(message="A descrição da Pauta deve ser informada")
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
