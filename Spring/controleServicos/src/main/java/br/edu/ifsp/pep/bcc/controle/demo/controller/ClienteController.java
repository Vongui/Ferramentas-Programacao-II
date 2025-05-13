package br.edu.ifsp.pep.bcc.controle.demo.controller;

import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.ClienteDTO;
import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.ClienteMapper;
import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.ClienteResponseDTO;
import br.edu.ifsp.pep.bcc.controle.demo.controller.exception.NoContent;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.Cliente;
import br.edu.ifsp.pep.bcc.controle.demo.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cliente")
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Cliente Controller", description = "gerenciamento do cliente")
public class ClienteController {

    private final ClienteService clientService;
    private final ClienteMapper clienteMapper;

    @Operation(summary = "Listar todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum cliente encontrado")
    })
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteResponseDTO>> getAll(){
        List<Cliente> listaClient = clientService.getAll();
        if (listaClient == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteMapper.clientListToClienteResponseDtoList(listaClient));
    }

    @Operation(summary = "Buscar cliente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteResponseDTO> getClient(@PathVariable int codigo){

        Cliente client = clientService.getClientById(codigo);
        if (client != null){
            return ResponseEntity.ok(clienteMapper.clientToClientResponseDTO(client));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Buscar clientes por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clientes encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado com o nome fornecido")
    })
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteResponseDTO>> getByNome(@RequestParam(value = "nome", defaultValue = "") String nome){
        List<Cliente> listaCliente = clientService.getClientByNome(nome);
        if (listaCliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clienteMapper.clientListToClienteResponseDtoList(listaCliente));
    }

    @Operation(summary = "Criar um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos para criação do cliente")
    })
    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteResponseDTO> createClient(@Valid @RequestBody ClienteDTO dto) throws Exception {
            log.debug("Criando um novo cliente...");
            Cliente client = clientService.create(clienteMapper.clientDtoTOClient(dto));
            log.info("Cliente criado com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.clientToClientResponseDTO(client));
    }

    @Operation(summary = "Excluir um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado para exclusão")
    })
    @DeleteMapping(value = "/{codigo}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteResponseDTO> deleteClient(@PathVariable int codigo){
        Cliente client = clientService.delete(codigo);
        if (client != null){
            return ResponseEntity.ok(clienteMapper.clientToClientResponseDTO(client));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualizar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado para atualização")
    })
    @PutMapping(value = "/{codigo}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<ClienteResponseDTO> updateClient(@PathVariable int codigo, @RequestBody ClienteDTO dto) {

        Cliente clientAlter = clientService.updateClient(codigo, clienteMapper.clientDtoTOClient(dto));
        return ResponseEntity.ok(clienteMapper.clientToClientResponseDTO(clientAlter));
    }

    @Operation(summary = "Ativar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente ativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado para ativação")
    })
    @PatchMapping(value = "/{codigo}/ativar")
    public ResponseEntity<String> activateClient(@PathVariable int codigo){
        if(clientService.alterStatus(codigo, 1)){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Desativar um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente desativado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado para desativação")
    })
    @PatchMapping(value = "/{codigo}/desativar")
    public ResponseEntity<String> deactivateClient(@PathVariable int codigo){
        if (clientService.alterStatus(codigo, 0)){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

}
