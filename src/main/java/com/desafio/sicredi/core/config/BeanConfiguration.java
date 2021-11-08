package com.desafio.sicredi.core.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.desafio.sicredi.api.assembleia.adapters.dto.request.SessaoAberturaDto;
import com.desafio.sicredi.api.assembleia.adapters.dto.response.SessaoRetornoDto;
import com.desafio.sicredi.api.assembleia.application.ports.AgendadorServicePort;
import com.desafio.sicredi.api.assembleia.application.ports.SessaoServicePort;
import com.desafio.sicredi.api.votacao.adapters.dto.request.VotoInclusaoDto;
import com.desafio.sicredi.api.votacao.adapters.dto.response.VotoRetornoDto;
import com.desafio.sicredi.core.domain.Sessao;
import com.desafio.sicredi.core.domain.Votacao;

@Configuration
public class BeanConfiguration {

	@Autowired
	AgendadorServicePort agendadorService;

	@Bean
	public ModelMapper modelMapper() {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(parseSessaDtoParaSessao());
		modelMapper.addMappings(parseVotacaoDtoParaVotacao());
		modelMapper.addMappings(parseVotacaoParaVotoRetornoDto());
		modelMapper.addConverter(converterSessaParaSessaoDto());
		return modelMapper;
	}

	@Bean
	public void configuraTimeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

	@Bean
	public void  agendarNotificacaoSessoesAbertas() {
		agendadorService.agendarTarefa();
	}

	private PropertyMap<SessaoAberturaDto, Sessao> parseSessaDtoParaSessao() {
		return new PropertyMap<SessaoAberturaDto, Sessao>() {
			@Override
			protected void configure() {
				map().getPauta().setId(source.getPauta());
			}
		};
	}

	private Converter<Sessao, SessaoRetornoDto> converterSessaParaSessaoDto() {
		return new Converter<Sessao, SessaoRetornoDto>() {

			@Override
			public SessaoRetornoDto convert(MappingContext<Sessao, SessaoRetornoDto> context) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = context.getSource().getDataHoraAbertura();

				SessaoRetornoDto sessaoDto = new SessaoRetornoDto();
				sessaoDto.setDuracao(context.getSource().getDuracao());
				sessaoDto.setPauta(context.getSource().getPauta().getId());
				sessaoDto.setId(context.getSource().getId());

				if (date != null) {
					String dataEmString = sdf.format(date);

					sessaoDto.setDataHoraAbertura(dataEmString);
				}
				return sessaoDto;
			}
		};
	}

	private PropertyMap<VotoInclusaoDto, Votacao> parseVotacaoDtoParaVotacao() {
		return new PropertyMap<VotoInclusaoDto, Votacao>() {
			@Override
			protected void configure() {
				map().getSessao().setId(source.getSessao());
				map().getAssociado().setCpf(source.getCpf());
			}
		};
	}

	private PropertyMap<Votacao, VotoRetornoDto> parseVotacaoParaVotoRetornoDto() {
		return new PropertyMap<Votacao, VotoRetornoDto>() {
			@Override
			protected void configure() {
				map().setAssociado(source.getAssociado().getId());
				map().setSessao(source.getSessao().getId());
			}
		};
	}

}
