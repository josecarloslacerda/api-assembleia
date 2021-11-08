package com.desafio.sicredi.api.associado.adapters.outbound.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.sicredi.core.domain.Associado;

@Repository
public interface AssociadoRepositorySpringData extends JpaRepository<Associado, Long>  {

	Optional<Associado> findByCpf(String cpf);

}
