package com.desafio.sicredi.api.associado.adapters.inbound.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.sicredi.api.associado.adapters.dto.request.AssociadoInclusaoDto;
import com.desafio.sicredi.api.associado.adapters.dto.response.AssociadoRetornoDto;
import com.desafio.sicredi.api.associado.application.ports.AssociadoServicePort;
import com.desafio.sicredi.core.domain.Associado;
import com.desafio.sicredi.core.exception.CPFMalFormatadoException;
import com.desafio.sicredi.core.patterns.Response;
import com.desafio.sicredi.core.util.Utils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Recursos relacionados a gestão dos Associados")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RestController
@RequestMapping("associados/v1")
public class AssociadoControllerV1 {

	private ModelMapper modelMapper;
	private AssociadoServicePort associadoService;

	public AssociadoControllerV1(ModelMapper modelMapper, AssociadoServicePort associadoService) {
		this.associadoService = associadoService;
		this.modelMapper = modelMapper;
	}

	@ApiOperation("Recurso responsável por cadastrar um Associado")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Associado inserido!", response = Response.class)})
	@PostMapping(value ="/associado",
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<AssociadoRetornoDto>> incluirAssociado(
			@RequestBody @ApiParam(name="Associado") AssociadoInclusaoDto associadoDto){

		validaCPFInformado(associadoDto.getCpf());

		Associado associado = modelMapper.map(associadoDto, Associado.class);

		associado = associadoService.save(associado);

		AssociadoRetornoDto associadoDtoRetorno = modelMapper.map(associado, AssociadoRetornoDto.class);

		Response<AssociadoRetornoDto> response = Response.Factory.<AssociadoRetornoDto>criar(associadoDtoRetorno);
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMensagem("Associado inserido!");

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@ApiOperation("Recurso responsável por obter todos os Associados cadastrados")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Associados retornados", response = Response.class)})
	@GetMapping(value ="/associado",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<List<AssociadoRetornoDto>>> obterListadeAssociados(){

		List<Associado> associados = associadoService.findAll();

		if (associados.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			List<AssociadoRetornoDto> associadosDto = modelMapper.map(associados,
					new TypeToken<List<AssociadoRetornoDto>>() {}.getType());
			Response<List<AssociadoRetornoDto>> response = Response.Factory.criar(associadosDto);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMensagem("Associados retornados");

			return ResponseEntity.ok().body(response);
		}
	}

	@ApiOperation("Recurso responsável por obter um Associado pelo CPF informado")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Associado retornado", response = Response.class)})
	@GetMapping("/associado/{cpf}")
	public ResponseEntity<Response<AssociadoRetornoDto>> obterAssociadoPorCPF(
			@PathVariable(value = "cpf", required = true, name = "cpf") @ApiParam(name="cpf", example="50960648089")
		String cpf){

		validaCPFInformado(cpf);

		Optional<Associado> associado = associadoService.findByCPF(cpf);

		if (associado.isPresent()) {
			AssociadoRetornoDto associadoDto = modelMapper.map(associado.get(),
					AssociadoRetornoDto.class);

			Response<AssociadoRetornoDto> response = Response.Factory.<AssociadoRetornoDto>criar(associadoDto);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMensagem("Associado retornado!");

			return ResponseEntity.ok().body(response);

		}

		return ResponseEntity.noContent().build();
	}

	private void validaCPFInformado(String cpf) {
		if (!Utils.contemApenasDigito(cpf) || cpf.length() != 11) {
			throw new CPFMalFormatadoException();
		}
	}

}
