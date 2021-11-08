package com.desafio.sicredi.core.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_pauta")
@AttributeOverrides({ @AttributeOverride(name="id", column = @Column(name = "pau_id_pauta")) })
public class Pauta extends Entidade {

	@Column(name="pau_descricao", nullable=false, length=400)
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
