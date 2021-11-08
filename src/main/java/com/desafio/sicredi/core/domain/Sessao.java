package com.desafio.sicredi.core.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_sessao")
@AttributeOverrides({ @AttributeOverride(name="id", column = @Column(name = "ses_id_sessao")) })
public class Sessao extends Entidade {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ses_data_hora_abertura", nullable=false)
	private Date dataHoraAbertura;

	@Column(name="ses_duracao", nullable=false)
	private Integer duracao;

	@OneToOne
	@JoinColumn(name="ses_fk_id_pauta", referencedColumnName="pau_id_pauta")
	private Pauta pauta;

	public Date getDataHoraAbertura() {
		return dataHoraAbertura;
	}

	public void setDataHoraAbertura(Date dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}

	public Integer getDuracao() {
		return duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

}
