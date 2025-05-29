package com.prova.api.controller.dto;

import com.prova.api.model.Pacotes;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PacotesMapper {

    PacotesMapper INSTANCE = Mappers.getMapper(PacotesMapper.class);

    Pacotes dtoToPacotes(PacotesDTO dto);

    PacotesDTO pacoteToDto(Pacotes pacotes);

    List<Pacotes> dtoToPacote(List<PacotesDTO> dtos);

    List<PacotesDTO> pacotesToDto(List<Pacotes> pacotes);

}
