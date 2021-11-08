package com.desafio.sicredi.api.associado.adapters.dto.request;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Associado", description="Objeto relacionado às informações do Associado")
public class AssociadoInclusaoDto {

	@ApiModelProperty(value = "Nome do associado",
			name = "nome",
			allowEmptyValue = false,
			required = true,
			example = "João")
	@NotEmpty(message="o Nome do Associado deve ser informado")
	private String nome;

	@ApiModelProperty(value = "CPF do associado. Deve ser informado sem máscara",
			name = "cpf",
			allowEmptyValue = false,
			required = true,
			example = "99999999999")
	@NotEmpty(message="o CPF do Associado deve ser informado")
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
