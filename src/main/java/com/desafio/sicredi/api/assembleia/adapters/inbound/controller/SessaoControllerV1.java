package com.desafio.sicredi.api.assembleia.adapters.inbound.controller;

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

import com.desafio.sicredi.api.assembleia.adapters.dto.request.SessaoAberturaDto;
import com.desafio.sicredi.api.assembleia.adapters.dto.response.SessaoRetornoDto;
import com.desafio.sicredi.api.assembleia.application.ports.AgendadorServicePort;
import com.desafio.sicredi.api.assembleia.application.ports.SessaoServicePort;
import com.desafio.sicredi.core.domain.Sessao;
import com.desafio.sicredi.core.patterns.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Recursos relacionados a gestão de Sessoes")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping("sessoes/v1")
public class SessaoControllerV1 {

	private ModelMapper modelMapper;
	private SessaoServicePort sessaoService;
	private AgendadorServicePort agendadorSessao;

	public SessaoControllerV1(final ModelMapper modelMapper,
			final SessaoServicePort sessaoService,
			final AgendadorServicePort agendadorSessao) {
		this.sessaoService = sessaoService;
		this.modelMapper = modelMapper;
		this.agendadorSessao = agendadorSessao;
	}

	@ApiOperation("Recurso responsável por abrir uma Sessao")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Sessao aberta", response = Response.class)})
	@PostMapping(value ="/abrir-sessao",
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<SessaoRetornoDto>> abrirSessao(
			@RequestBody @ApiParam(name="Sessao") SessaoAberturaDto sessaoDto){

		Sessao sessao = modelMapper.map(sessaoDto, Sessao.class);

		sessao = sessaoService.abrirSessao(sessao);

		incluirAgendamentoParaNotificacaoDeFimDeSessao(sessao);

		SessaoRetornoDto sessaoDtoRetorno = modelMapper.map(sessao, SessaoRetornoDto.class);

		Response<SessaoRetornoDto> response = Response.Factory.<SessaoRetornoDto>criar(sessaoDtoRetorno);
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMensagem("Sessao aberta");

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@ApiOperation("Recurso responsável por obter todas as Sessoes cadastradas")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Sessoes retornadas", response = Response.class),
			@ApiResponse(code = 204, message = "Não há Sessoes cadastradas")})
	@GetMapping(value ="/sessao",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<List<SessaoRetornoDto>>> obterSessoes(){

		List<Sessao> sessoes = sessaoService.findAll();

		if (sessoes.isEmpty()) {
			return ResponseEntity.noContent().build();

		} else {
			List<SessaoRetornoDto> sessoesDto = modelMapper.map(sessoes,
					new TypeToken<List<SessaoRetornoDto>>() {}.getType());
			Response<List<SessaoRetornoDto>> response = Response.Factory.criar(sessoesDto);

			response.setStatusCode(HttpStatus.OK.value());
			response.setMensagem("Sessoes retornadas");

			return ResponseEntity.ok().body(response);
		}

	}

	@ApiOperation("Recurso responsável por obter uma Sessão pelo id informado")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Sessão retornada", response = Response.class),
			@ApiResponse(code = 204, message = "Sessão não encontrada")})
	@GetMapping("/sessao/{id}")
	public ResponseEntity<Response<SessaoRetornoDto>> obterPautaPorId(
			@PathVariable(value = "id", required = true, name = "id") @ApiParam(name="id") Long id){

		Optional<Sessao> sessao = sessaoService.findById(id);

		if (sessao.isPresent()) {
			SessaoRetornoDto sessaoDto = modelMapper.map(sessao.get(),
					SessaoRetornoDto.class);

			Response<SessaoRetornoDto> response = Response.Factory.<SessaoRetornoDto>criar(sessaoDto);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMensagem("Sessao retornada");

			return ResponseEntity.ok().body(response);

		}

		return ResponseEntity.noContent().build();

	}

	private void incluirAgendamentoParaNotificacaoDeFimDeSessao(Sessao sessao) {
		agendadorSessao.agendarTarefa(sessao);
	}

}
