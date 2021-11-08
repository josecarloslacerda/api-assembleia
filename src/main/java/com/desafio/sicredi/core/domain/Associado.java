package com.desafio.sicredi.core.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_associado")
@AttributeOverrides({ @AttributeOverride(name="id", column = @Column(name = "ass_id_associado")) })
public class Associado extends Entidade {

	@Column(name="ass_nome", nullable=false, length=200)
	private String nome;

	@Column(name="ass_cpf", nullable=false, length=11, unique=true)
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
