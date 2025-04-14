package br.edu.ifsp.pep.bcc.controle.demo.controller;

import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServico;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServicoId;
import br.edu.ifsp.pep.bcc.controle.demo.service.ItemOrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item-ordem-servico")
@RequiredArgsConstructor
@Tag(name = "Item Ordem de Serviço", description = "Gerenciamento de Itens de Ordens de Serviço")
public class ItemOrdemServicoController {

    private final ItemOrdemServicoService service;

    @Operation(summary = "Listar todos os itens de ordens de serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de itens retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum item encontrado")
    })
    @GetMapping
    public ResponseEntity<List<ItemOrdemServico>> getAll() {
        List<ItemOrdemServico> itens = service.findAll();
        if (itens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(itens);
    }

    @Operation(summary = "Buscar item por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado")
    })
    @GetMapping("/{ordemServicoId}/{numeroItem}")
    public ResponseEntity<ItemOrdemServico> getById(@PathVariable int ordemServicoId, @PathVariable int numeroItem) {
        ItemOrdemServicoId id = new ItemOrdemServicoId(ordemServicoId, numeroItem);
        ItemOrdemServico item = service.findById(id);
        return ResponseEntity.ok(item);
    }

    @Operation(summary = "Criar um novo item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do item")
    })
    @PostMapping
    public ResponseEntity<ItemOrdemServico> create(@RequestBody ItemOrdemServico item) {
        ItemOrdemServico novoItem = service.create(item);
        return ResponseEntity.status(201).body(novoItem);
    }

//    @Operation(summary = "Atualizar um item")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Item não encontrado para atualização")
//    })
//    @PutMapping("/{ordemServicoId}/{numeroItem}")
//    public ResponseEntity<ItemOrdemServico> update(@PathVariable int ordemServicoId, @PathVariable int numeroItem, @RequestBody ItemOrdemServico item) {
//        ItemOrdemServicoId id = new ItemOrdemServicoId(ordemServicoId, numeroItem);
//        ItemOrdemServico itemAtualizado = service.update(id, item);
//        return ResponseEntity.ok(itemAtualizado);
//    }

    @Operation(summary = "Excluir um item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado para exclusão")
    })
    @DeleteMapping("/{ordemServicoId}/{numeroItem}")
    public ResponseEntity<Void> delete(@PathVariable int ordemServicoId, @PathVariable int numeroItem) {
        ItemOrdemServicoId id = new ItemOrdemServicoId(ordemServicoId, numeroItem);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
