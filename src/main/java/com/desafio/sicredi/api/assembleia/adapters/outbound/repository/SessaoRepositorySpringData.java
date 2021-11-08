package com.desafio.sicredi.api.assembleia.adapters.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.sicredi.core.domain.Sessao;

@Repository
public interface SessaoRepositorySpringData extends JpaRepository<Sessao, Long> {

	@Query("select s from Sessao s join fetch s.pauta p where p.id = :id")
	Optional<Sessao> findByIdPauta(@Param("id") Long idPauta);

	@Query("select s from Sessao s join fetch s.pauta")
	List<Sessao> findAll();

}
