package com.desafio.sicredi.core.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.desafio.sicredi.core.domain.enuns.OpcaoVotacaoEnum;

@Entity
@Table(name="tb_votacao")
@AttributeOverrides({ @AttributeOverride(name="id", column = @Column(name = "vot_id_votacao")) })
public class Votacao extends Entidade {

	@ManyToOne
	@JoinColumn(name="vot_fk_id_sessao", referencedColumnName="ses_id_sessao")
	private Sessao sessao;

	@ManyToOne
	@JoinColumn(name="vot_fk_id_associado", referencedColumnName="ass_id_associado")
	private Associado associado;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="vot_voto")
	private OpcaoVotacaoEnum voto;

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public OpcaoVotacaoEnum getVoto() {
		return voto;
	}

	public void setVoto(OpcaoVotacaoEnum voto) {
		this.voto = voto;
	}
}
