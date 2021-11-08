package com.desafio.sicredi.api.votacao.adapters.dto.response;

import com.desafio.sicredi.core.patterns.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="VotosContabilizados", description="Objeto relacionado a contagem dos votos de uma Sessão")
public class VotosContabilizadoDto extends DTO {

	@ApiModelProperty(value = "Código de identificação da Sessão",
			name = "idSessao",
			example = "1")
	private Long idSessao;

	@ApiModelProperty(value = "Texto da Pauta",
			name = "descricaoPauta",
			example = "Texto descritivo da S")
	private String descricaoPauta;

	@ApiModelProperty(value = "Quantidade total de votos",
			name = "totalVotos",
			example = "10")
	private Integer totalVotos;

	@ApiModelProperty(value = "Quantidade de votos a favor",
			name = "votosAFavor",
			example = "6")
	private Long votosAFavor;

	@ApiModelProperty(value = "Quantidade de votos contra",
			name = "votosContra",
			example = "4")
	private Long votosContra;

	public Long getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(Long idSessao) {
		this.idSessao = idSessao;
	}

	public String getDescricaoPauta() {
		return descricaoPauta;
	}
	public void setDescricaoPauta(String descricaoPauta) {
		this.descricaoPauta = descricaoPauta;
	}
	public Integer getTotalVotos() {
		return totalVotos;
	}
	public void setTotalVotos(Integer totalVotos) {
		this.totalVotos = totalVotos;
	}
	public Long getVotosAFavor() {
		return votosAFavor;
	}
	public void setVotosAFavor(Long votosAFavor) {
		this.votosAFavor = votosAFavor;
	}
	public Long getVotosContra() {
		return votosContra;
	}
	public void setVotosContra(Long votosContra) {
		this.votosContra = votosContra;
	}



}
