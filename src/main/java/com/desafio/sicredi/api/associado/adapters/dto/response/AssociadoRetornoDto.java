package com.desafio.sicredi.api.associado.adapters.dto.response;

import com.desafio.sicredi.core.patterns.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="AssociadoRetorno", description="Objeto relacionado às informações do Associado")
public class AssociadoRetornoDto extends DTO {

	@ApiModelProperty(value = "Código de identificação do Associado",
			name = "id",
			example = "1")
	private Long id;

	@ApiModelProperty(value = "Nome do associado",
			name = "nome",
			example = "João")
	private String nome;

	@ApiModelProperty(value = "CPF do associado. Deve ser informado sem máscara",
			name = "cpf",
			example = "99999999999")
	private String cpf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
