package com.desafio.sicredi.api.assembleia.adapters.dto.response;

import javax.validation.constraints.NotEmpty;

import com.desafio.sicredi.core.patterns.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Pauta", description="Objeto relacionado às informações da Pauta")
public class PautaRetornoDto extends DTO {

	@ApiModelProperty(value = "Código de identificação da Pauta",
			name = "id",
			example = "1")
	private Long id;

	@ApiModelProperty(value = "Descrição da Pauta",
			name = "nome",
			allowEmptyValue = false,
			required = true,
			example = "texto da Pauta")
	@NotEmpty(message="A descrição da Pauta deve ser informada")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
