package com.desafio.sicredi.core.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entidade {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	public Entidade() {}

	public Entidade(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
