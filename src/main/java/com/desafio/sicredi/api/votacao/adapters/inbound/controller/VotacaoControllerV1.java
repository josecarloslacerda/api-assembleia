package com.desafio.sicredi.api.votacao.adapters.inbound.controller;

import java.util.List;

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

import com.desafio.sicredi.api.associado.adapters.dto.response.AssociadoRetornoDto;
import com.desafio.sicredi.api.votacao.adapters.dto.request.VotoInclusaoDto;
import com.desafio.sicredi.api.votacao.adapters.dto.response.VotoRetornoDto;
import com.desafio.sicredi.api.votacao.adapters.dto.response.VotosContabilizadoDto;
import com.desafio.sicredi.api.votacao.application.ports.VotacaoServicePort;
import com.desafio.sicredi.core.domain.Votacao;
import com.desafio.sicredi.core.patterns.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Recursos relacionados a gestão dos Votos")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping("votacao/v1")
public class VotacaoControllerV1 {

	private ModelMapper modelMapper;
	private VotacaoServicePort votacaoService;

	public VotacaoControllerV1(ModelMapper modelMapper, VotacaoServicePort associadoService) {
		this.votacaoService = associadoService;
		this.modelMapper = modelMapper;
	}

	@ApiOperation("Recurso responsável por cadastrar um voto")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Voto imputado", response = Response.class)})
	@PostMapping(value ="/votar",
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<VotoRetornoDto>> votar(
			@RequestBody @ApiParam(name="Voto") VotoInclusaoDto votoDto){

		Votacao voto = modelMapper.map(votoDto, Votacao.class);

		voto = votacaoService.votar(voto);

		VotoRetornoDto votoDtoRetorno = modelMapper.map(voto, VotoRetornoDto.class);

		Response<VotoRetornoDto> response = Response.Factory.<VotoRetornoDto>criar(votoDtoRetorno);
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMensagem("Voto imputado");

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@ApiOperation("Recurso responsável por obter todos os votos de uma Sessão")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Votações retornados", response = Response.class)})
	@GetMapping(value ="/votacao/{idSessao}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<List<VotoRetornoDto>>> obterVotosPorSessao(
			@PathVariable(value = "idSessao", required = true) Long idSessao){

		List<Votacao> votacoes = votacaoService.findBySessao(idSessao);
		List<VotoRetornoDto> votacoesDto = modelMapper.map(votacoes,
				new TypeToken<List<AssociadoRetornoDto>>() {}.getType());
		Response<List<VotoRetornoDto>> response = Response.Factory.criar(votacoesDto);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMensagem("Votações retornadas");

		return ResponseEntity.ok().body(response);
	}

	@ApiOperation("Recurso responsável por obter a conbabilização dos votos de uma Sessão")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Contabilização retornado", response = Response.class)})
	@GetMapping("/contabilizar/{idSessao}")
	public ResponseEntity<Response<VotosContabilizadoDto>> obterVotosContabilizados(
			@PathVariable(value = "idSessao", required = true) Long idSessao){

		VotosContabilizadoDto votosContabilizados = votacaoService.contabilizarVotacao(idSessao);

		Response<VotosContabilizadoDto> response = Response.Factory.<VotosContabilizadoDto>criar(votosContabilizados);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMensagem("Contabilização retornado");

		return ResponseEntity.ok().body(response);

	}

}
