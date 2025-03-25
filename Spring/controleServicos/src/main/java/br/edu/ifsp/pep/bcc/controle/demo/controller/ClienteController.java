package br.edu.ifsp.pep.bcc.controle.demo.controller;

import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.ClienteDTO;
import br.edu.ifsp.pep.bcc.controle.demo.controller.dto.ClienteResponseDTO;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.Cliente;
import br.edu.ifsp.pep.bcc.controle.demo.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
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

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getAll(){
        List<Cliente> listaClient = clientService.getAll();
        if (listaClient.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaClient);
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> getClient(@PathVariable int codigo){

        Cliente client = clientService.getClientById(codigo);
        if (client != null){
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getByNome(@RequestParam(value = "nome", defaultValue = "") String nome){
        List<Cliente> listaCliente = clientService.getClientByNome(nome);
        if (listaCliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaCliente);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createClient(@Valid @RequestBody ClienteDTO entity) throws Exception {
        log.debug("Criando um novo cliente...");
        ModelMapper mapper = new ModelMapper().registerModule(new RecordModule());
        Cliente clientMapperDTO = mapper.map(entity, Cliente.class);

        try{
            Cliente client = clientService.create(clientMapperDTO);
            log.info("Cliente criado com sucesso!");
            mapper = new ModelMapper().registerModule(new RecordModule());
            ClienteResponseDTO responseDTO = mapper.map(client, ClienteResponseDTO.class);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        }catch (Exception e){
            log.debug("Erro ao criar um novo cliente");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{codigo}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> deleteClient(@PathVariable int codigo){
        Cliente client = clientService.delete(codigo);
        if (client != null){
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{codigo}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Cliente> updateClient(@PathVariable int codigo, @RequestBody ClienteDTO dto) {
        ModelMapper mapper = new ModelMapper().registerModule(new RecordModule());
        Cliente client = mapper.map(dto, Cliente.class);

        Cliente clientAlter = clientService.updateClient(codigo, client);
        return ResponseEntity.ok(clientAlter);
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
