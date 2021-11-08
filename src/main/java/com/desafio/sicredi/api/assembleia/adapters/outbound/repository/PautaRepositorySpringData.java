package com.desafio.sicredi.api.assembleia.adapters.outbound.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.sicredi.core.domain.Pauta;

@Repository
public interface PautaRepositorySpringData extends JpaRepository<Pauta, Long> {

}
