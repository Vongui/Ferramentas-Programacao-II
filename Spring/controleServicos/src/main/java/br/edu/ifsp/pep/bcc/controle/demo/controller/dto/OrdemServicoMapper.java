package br.edu.ifsp.pep.bcc.controle.demo.controller.dto;

import br.edu.ifsp.pep.bcc.controle.demo.model.entities.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrdemServicoMapper {

    OrdemServicoMapper INSTANCE = Mappers.getMapper(OrdemServicoMapper.class);

    @Mapping(source = "cliente.codigo", target = "clienteId")
    OrdemServicoDTO OrdemServicoToDto(OrdemServico ordemServico);

    OrdemServico DtoToOrdemServico(OrdemServicoDTO ordemServicoDTO);

    List<OrdemServicoDTO> listOrdemServToListOrdemServDto(List<OrdemServico> ordemServicoList);
}
