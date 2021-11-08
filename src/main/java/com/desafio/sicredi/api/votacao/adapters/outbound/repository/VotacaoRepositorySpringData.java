package com.desafio.sicredi.api.votacao.adapters.outbound.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.sicredi.core.domain.Votacao;

@Repository
public interface VotacaoRepositorySpringData extends JpaRepository<Votacao, Long> {

	@Query("select v from Votacao v join fetch v.sessao s where s.id = :id")
	List<Votacao> findBySessao(@Param("id") Long idSessao);

	@Query("select v from Votacao v join fetch v.associado ass join fetch v.sessao s "
			+ " where ass.id = :idAssociado "
			+ " and s.id = :idSessao")
	Optional<Votacao> findByAssociadoAndSessao(@Param("idAssociado") Long idAssociado,
			@Param("idSessao") Long idSessao);

}
