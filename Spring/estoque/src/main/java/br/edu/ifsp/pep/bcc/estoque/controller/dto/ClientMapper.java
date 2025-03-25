package br.edu.ifsp.pep.bcc.estoque.controller.dto;

import br.edu.ifsp.pep.bcc.estoque.model.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client clientDTOTOClient(ClientDTO clientDTO);

    ClientDTO clientToClientDTO(Client client);

    ClientResponseDTO clientToClientResponseDTO(Client client);

}
