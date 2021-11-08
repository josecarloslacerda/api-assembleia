package com.desafio.sicredi.api.votacao.adapters.dto.response;

import com.desafio.sicredi.core.domain.enuns.OpcaoVotacaoEnum;
import com.desafio.sicredi.core.patterns.DTO;

import io.swagger.annotations.ApiModelProperty;

public class VotoRetornoDto extends DTO{

	@ApiModelProperty(value = "Código de identificação do Voto",
			name = "id",
			example = "1")
	private Long id;

	@ApiModelProperty(value = "Código de identificação do Associado",
			name = "associado",
			example = "1")
	private Long associado;

	@ApiModelProperty(value = "Código de identificação da Sessão",
			name = "sessao",
			example = "1")
	private Long sessao;

	@ApiModelProperty(value = "Informação de Voto",
			name = "voto",
			example = "SIM")
	private OpcaoVotacaoEnum voto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssociado() {
		return associado;
	}

	public void setAssociado(Long associado) {
		this.associado = associado;
	}

	public Long getSessao() {
		return sessao;
	}

	public void setSessao(Long sessao) {
		this.sessao = sessao;
	}

	public OpcaoVotacaoEnum getVoto() {
		return voto;
	}

	public void setVoto(OpcaoVotacaoEnum voto) {
		this.voto = voto;
	}


}
