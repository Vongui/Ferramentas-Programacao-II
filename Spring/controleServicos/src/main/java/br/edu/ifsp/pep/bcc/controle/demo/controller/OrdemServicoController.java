package br.edu.ifsp.pep.bcc.controle.demo.controller;

import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.OrdemServicoDTO;
import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.OrdemServicoMapper;
import br.edu.ifsp.pep.bcc.controle.demo.controller.exception.NoContent;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.Cliente;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.OrdemServico;
import br.edu.ifsp.pep.bcc.controle.demo.service.ClienteService;
import br.edu.ifsp.pep.bcc.controle.demo.service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ordem-servico")
@RequiredArgsConstructor
@Tag(name = "Ordem de Serviço", description = "Gerenciamento de Ordens de Serviço")
public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService;
    private final OrdemServicoMapper ordemServicoMapper;
    private final ClienteService clienteService;

    @Operation(summary = "Listar todas as ordens de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ordens de serviço retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma ordem de serviço encontrada")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrdemServicoDTO>> getAll() throws NoContent {
        List<OrdemServico> ordens = ordemServicoService.findAll();
        if (ordens == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(ordemServicoMapper.listOrdemServToListOrdemServDto(ordens));
    }

    @Operation(summary = "Buscar ordem de serviço por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de serviço encontrada"),
            @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada")
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdemServicoDTO> getById(@PathVariable Integer id) {
        OrdemServico ordem = ordemServicoService.findById(id);
        if (ordem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ordemServicoMapper.OrdemServicoToDto(ordem));
    }

    @Operation(summary = "Atualizar uma ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de serviço atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada para atualização")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdemServicoDTO> update(@PathVariable Integer id, @RequestBody OrdemServicoDTO ordemServicoDTO) {
        OrdemServico ordemAtualizada = ordemServicoMapper.DtoToOrdemServico(ordemServicoDTO);
        OrdemServico ordem = ordemServicoService.update(id, ordemAtualizada);
        return ResponseEntity.ok(ordemServicoMapper.OrdemServicoToDto(ordem));
    }

    @Operation(summary = "Excluir uma ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ordem de serviço excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem de serviço não encontrada para exclusão")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        ordemServicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Criar uma nova ordem de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ordem de serviço criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação da ordem de serviço")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrdemServicoDTO> create(@RequestBody OrdemServicoDTO ordemServicoDTO) {
        OrdemServico ordemServico = ordemServicoMapper.DtoToOrdemServico(ordemServicoDTO);
        Cliente cliente = clienteService.getClientById(ordemServicoDTO.clienteId());
        if (cliente == null) {
            return ResponseEntity.badRequest().build();
        }
        ordemServico.setCliente(cliente);
        OrdemServico ordemCriada = ordemServicoService.create(ordemServico);
        return ResponseEntity.status(201).body(ordemServicoMapper.OrdemServicoToDto(ordemCriada));
    }

}
