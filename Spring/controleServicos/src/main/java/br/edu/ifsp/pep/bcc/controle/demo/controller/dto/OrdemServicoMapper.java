package br.edu.ifsp.pep.bcc.controle.demo.controller.dto;

import br.edu.ifsp.pep.bcc.controle.demo.model.entities.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrdemServicoMapper {

    OrdemServicoMapper INSTANCE = Mappers.getMapper(OrdemServicoMapper.class);

    OrdemServico DtoToOrdemServico(OrdemServicoDTO ordemServicoDTO);

    OrdemServicoDTO OrdemServicoToDto(OrdemServico ordemServico);
}
