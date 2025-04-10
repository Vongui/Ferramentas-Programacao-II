package br.edu.ifsp.pep.bcc.controle.demo.controller;

import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.ClienteDTO;
import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.ClienteMapper;
import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.ClienteResponseDTO;
import br.edu.ifsp.pep.bcc.controle.demo.controller.exception.NoContent;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.Cliente;
import br.edu.ifsp.pep.bcc.controle.demo.service.ClienteService;
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
public class ClienteController {

    private final ClienteService clientService;
    private final ClienteMapper clienteMapper;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteResponseDTO>> getAll(){
        List<Cliente> listaClient = clientService.getAll();
        if (listaClient == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienteMapper.clientListToClienteResponseDtoList(listaClient));
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteResponseDTO> getClient(@PathVariable int codigo){

        Cliente client = clientService.getClientById(codigo);
        if (client != null){
            return ResponseEntity.ok(clienteMapper.clientToClientResponseDTO(client));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteResponseDTO>> getByNome(@RequestParam(value = "nome", defaultValue = "") String nome){
        List<Cliente> listaCliente = clientService.getClientByNome(nome);
        if (listaCliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clienteMapper.clientListToClienteResponseDtoList(listaCliente));
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteResponseDTO> createClient(@Valid @RequestBody ClienteDTO dto) throws Exception {
            log.debug("Criando um novo cliente...");
            Cliente client = clientService.create(clienteMapper.clientDtoTOClient(dto));
            log.info("Cliente criado com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteMapper.clientToClientResponseDTO(client));
    }

    @DeleteMapping(value = "/{codigo}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteResponseDTO> deleteClient(@PathVariable int codigo){
        Cliente client = clientService.delete(codigo);
        if (client != null){
            return ResponseEntity.ok(clienteMapper.clientToClientResponseDTO(client));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{codigo}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<ClienteResponseDTO> updateClient(@PathVariable int codigo, @RequestBody ClienteDTO dto) {

        Cliente clientAlter = clientService.updateClient(codigo, clienteMapper.clientDtoTOClient(dto));
        return ResponseEntity.ok(clienteMapper.clientToClientResponseDTO(clientAlter));
    }

    @PatchMapping(value = "/{codigo}/ativar")
    public ResponseEntity<String> activateClient(@PathVariable int codigo){
        if(clientService.alterStatus(codigo, 1)){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "/{codigo}/desativar")
    public ResponseEntity<String> deactivateClient(@PathVariable int codigo){
        if (clientService.alterStatus(codigo, 0)){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

}
