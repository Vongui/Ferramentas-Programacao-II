package br.edu.ifsp.pep.bcc.estoque.controller;

import br.edu.ifsp.pep.bcc.estoque.controller.dto.ClientDTO;
import br.edu.ifsp.pep.bcc.estoque.controller.dto.ClientResponseDTO;
import br.edu.ifsp.pep.bcc.estoque.model.entities.Client;
import br.edu.ifsp.pep.bcc.estoque.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("client")
@Slf4j
@RequiredArgsConstructor
public class ClientController {

    //@PathVariable: Para capturar valores diretamente do caminho da URL.
    //@RequestParam: Para capturar parâmetros de consulta (query parameters) da URL.

    /*
    GET -> body vazio, retorna JSON
    POST -> entidade inteira, INSERT
    DELETE -> código=> BODY vazio, Deleta a entidade
    PUT -> entidade inteira, altera a entidade inteira
    PATCH -> Query Params, Path Params, Body, altera somente o atributo da entidade
    */


    private final ClientService clientService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAll(){
        List<Client> listaClient = clientService.getAll();
        if (listaClient.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaClient);
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable int codigo){

        Client client = clientService.getClientById(codigo);
        if (client != null){
            return ResponseEntity.ok(client);
        }
        return ResponseEntity.notFound().build(); //utilizar build se não passar um body para o erro
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getByNome(@RequestParam(value = "nome", defaultValue = "") String nome){
        List<Client> listaCliente = clientService.getClientByNome(nome);
        if (listaCliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaCliente);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createClient(@Valid @RequestBody ClientDTO entity) throws Exception {
        log.debug("Criando um novo cliente...");
        try{
            Client client = clientService.create(entity.tranformaParaObjeto());
            log.info("Cliente criado com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body(ClientResponseDTO.transformaEmDto(client));
        }catch (Exception e){
            log.debug("Erro ao criar um novo cliente");
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        //URI uriAddress = UriComponentsBuilder.fromPath("/client/{codigo}").
                //buildAndExpand(entity.tranformaParaObjeto().getCodigo()).toUri()
    }

    @DeleteMapping(value = "/{codigo}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> deleteClient(@PathVariable int codigo){
        Client client = clientService.delete(codigo);
       if (client != null){
           return ResponseEntity.ok(client);
       }
       return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{codigo}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Client> updateClient(@PathVariable int codigo, @RequestBody Client client){
        Client clientAlter = clientService.updateClient(codigo, client);
        if (clientAlter != null){
            return ResponseEntity.ok(clientAlter);
        }
        return ResponseEntity.notFound().build();
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
