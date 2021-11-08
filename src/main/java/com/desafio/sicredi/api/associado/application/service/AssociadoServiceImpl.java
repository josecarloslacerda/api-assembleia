package com.desafio.sicredi.api.associado.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.sicredi.api.associado.application.ports.AssociadoRepositoryPort;
import com.desafio.sicredi.api.associado.application.ports.AssociadoServicePort;
import com.desafio.sicredi.api.associado.application.ports.ValidacaoCPFServicePort;
import com.desafio.sicredi.core.domain.Associado;
import com.desafio.sicredi.core.exception.AssociadoNaoAptoAssembleiaException;
import com.desafio.sicredi.core.exception.CPFInformadoJaCadastradoException;

@Service
public class AssociadoServiceImpl implements AssociadoServicePort {

	private AssociadoRepositoryPort associadoRepository;
	private ValidacaoCPFServicePort validacaoCpfService;

	@Autowired
	private AssociadoServicePort associadoService;

	public AssociadoServiceImpl(final AssociadoRepositoryPort associadoRepository,
			final ValidacaoCPFServicePort validacaoCpfService) {
		this.associadoRepository = associadoRepository;
		this.validacaoCpfService = validacaoCpfService;
	}

	@Override
	@Transactional
	public Associado save(Associado associado) {

		verificaAptidaoCPFDoAssociado(associado.getCpf());
		verificaSeExisteCPFCadastrado(associado.getCpf());

		return associadoRepository.save(associado);
	}

	@Override
	public List<Associado> findAll() {
		return associadoRepository.findAll();
	}

	@Override
	public Optional<Associado> findById(Long id) {
		return associadoRepository.findById(id);
	}

	@Override
	public Optional<Associado> findByCPF(String cpf) {
		return associadoRepository.findByCPF(cpf);
	}

	private void verificaAptidaoCPFDoAssociado(String cpf) {
		if (!validacaoCpfService.validaCPF(cpf)) {
			throw new AssociadoNaoAptoAssembleiaException();
		}
	}

	private void verificaSeExisteCPFCadastrado(String cpf) {
		Optional<Associado> associado = associadoService.findByCPF(cpf);
		if (associado.isPresent()) {
			throw new CPFInformadoJaCadastradoException();
		}
	}

}
