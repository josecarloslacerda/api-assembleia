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

import com.desafio.sicredi.api.assembleia.adapters.dto.request.PautaInclusaoDto;
import com.desafio.sicredi.api.assembleia.adapters.dto.response.PautaRetornoDto;
import com.desafio.sicredi.api.assembleia.application.ports.PautaServicePort;
import com.desafio.sicredi.core.domain.Pauta;
import com.desafio.sicredi.core.patterns.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Recursos relacionados a gestão de Pautas")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
@RestController
@RequestMapping("pautas/v1")
public class PautaControllerV1 {

	private ModelMapper modelMapper;
	private PautaServicePort pautaService;

	public PautaControllerV1(final ModelMapper modelMapper, final PautaServicePort pautaService) {
		this.pautaService = pautaService;
		this.modelMapper = modelMapper;
	}

	@ApiOperation("Recurso responsável por cadastrar uma Pauta")
	@ApiResponses(value = {@ApiResponse(code = 201, message = "Pauta inserida!", response = Response.class)})
	@PostMapping(value ="/pauta",
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<PautaRetornoDto>> incluirPauta(
			@RequestBody @ApiParam(name="Pauta") PautaInclusaoDto pautaDto){

		Pauta pauta = modelMapper.map(pautaDto, Pauta.class);

		pauta = pautaService.save(pauta);

		PautaRetornoDto pautaDtoRetorno = modelMapper.map(pauta, PautaRetornoDto.class);

		Response<PautaRetornoDto> response = Response.Factory.<PautaRetornoDto>criar(pautaDtoRetorno);
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMensagem("Pauta inserida!");

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@ApiOperation("Recurso responsável por obter todas as Pautas cadastradas")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Pautas retornadas", response = Response.class),
			@ApiResponse(code = 204, message = "Não há Pautas cadastradas")})
	@GetMapping(value ="/pauta",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Response<List<PautaRetornoDto>>> obterPautas(){

		List<Pauta> pautas = pautaService.findAll();

		if (pautas.isEmpty()) {
			return ResponseEntity.noContent().build();

		} else {
			List<PautaRetornoDto> pautasDto = modelMapper.map(pautas,
					new TypeToken<List<PautaRetornoDto>>() {}.getType());
			Response<List<PautaRetornoDto>> response = Response.Factory.criar(pautasDto);

			response.setStatusCode(HttpStatus.OK.value());
			response.setMensagem("Pautas retornadas");

			return ResponseEntity.ok().body(response);
		}

	}

	@ApiOperation("Recurso responsável por obter uma Pauta pelo id informado")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Pauta retornada", response = Response.class),
			@ApiResponse(code = 204, message = "Pauta não encontrada")})
	@GetMapping("/pauta/{id}")
	public ResponseEntity<Response<PautaRetornoDto>> obterPautaPorId(
			@PathVariable(value = "id", required = true, name = "id") @ApiParam(name="id") Long id){

		Optional<Pauta> pauta = pautaService.findById(id);

		if (pauta.isPresent()) {
			PautaRetornoDto pautaDto = modelMapper.map(pauta.get(),
					PautaRetornoDto.class);

			Response<PautaRetornoDto> response = Response.Factory.<PautaRetornoDto>criar(pautaDto);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMensagem("Pauta retornada!");

			return ResponseEntity.ok().body(response);

		}

		return ResponseEntity.noContent().build();


	}

}
