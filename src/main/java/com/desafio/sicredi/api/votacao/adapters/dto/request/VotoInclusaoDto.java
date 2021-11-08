package com.desafio.sicredi.api.votacao.adapters.dto.request;

import javax.validation.constraints.NotEmpty;

import com.desafio.sicredi.core.domain.enuns.OpcaoVotacaoEnum;
import com.desafio.sicredi.core.patterns.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="Voto", description="Objeto relacionado às informações de um Voto")
public class VotoInclusaoDto extends DTO {

	@ApiModelProperty(value = "Código de identificaççao da Sessão",
			name = "sessao",
			allowEmptyValue = false,
			required = true,
			example = "1")
	@NotEmpty(message="O código de identificação da Sessão deve ser informado")
	private Long sessao;

	@ApiModelProperty(value = "CPF do Associado cadastrado",
			name = "cpf",
			allowEmptyValue = false,
			required = true,
			example = "99999999999")
	@NotEmpty(message="CPF do Associado cadastrado deve ser informado")
	private String cpf;

	@ApiModelProperty(value = "Opção de voto",
			name = "voto",
			allowEmptyValue = false,
			required = true,
			example = "SIM")
	private OpcaoVotacaoEnum voto;

	public Long getSessao() {
		return sessao;
	}

	public void setSessao(Long sessao) {
		this.sessao = sessao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public OpcaoVotacaoEnum getVoto() {
		return voto;
	}

	public void setVoto(OpcaoVotacaoEnum voto) {
		this.voto = voto;
	}

}
