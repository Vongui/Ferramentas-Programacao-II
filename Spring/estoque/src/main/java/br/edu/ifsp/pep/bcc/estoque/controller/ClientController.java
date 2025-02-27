package br.edu.ifsp.pep.bcc.estoque.controller;

import br.edu.ifsp.pep.bcc.estoque.model.entities.Client;
import br.edu.ifsp.pep.bcc.estoque.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("client")
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

    @Autowired
    ClientRepository clientRepository;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAll(){

        List<Client> listaClientes = clientRepository.findAll();
        if (listaClientes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return ResponseEntity.ok(listaClientes);
    }

    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable int codigo){

        Optional<Client> optionalClient = clientRepository.findById(codigo);
        if (optionalClient.isPresent()){
            return ResponseEntity.ok(optionalClient.get());
        }

        return ResponseEntity.notFound().build(); //utilizar build se não passar um body para o erro
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getByNome(@RequestParam(value = "nome", defaultValue = "") String nome){
        List<Client> listaCliente = clientRepository.findByNomeLike("%"+nome+"%");
        if (listaCliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listaCliente);
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Client createClient(@RequestBody Client entity){

        clientRepository.save(entity);
        return entity;
    }

    @DeleteMapping(value = "/{codigo}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> deleteClient(@PathVariable int codigo){
        Optional<Client> cliente = clientRepository.findById(codigo);
        if (cliente.isPresent()){
            clientRepository.delete(cliente.get());
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{codigo}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@PathVariable int codigo, @RequestBody Client client){
        Optional<Client> optionalClient = clientRepository.findById(client.getCodigo());

        if (optionalClient.isPresent()){
            client.setCodigo(codigo);
            Client clientAlter = clientRepository.save(client);
            return ResponseEntity.ok(clientAlter);
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "/{codigo}/ativar")
    public ResponseEntity<String> activateClient(@PathVariable int codigo){
        Optional<Client> optionalCliente = clientRepository.findById(codigo);
        if (optionalCliente.isPresent()){ //cliente encontrado
            Client c = optionalCliente.get(); // extraindo a entity do Optional
            c.setAtivo(1);
            clientRepository.save(c);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "/{codigo}/desativar")
    public ResponseEntity<String> deactivateClient(@PathVariable int codigo){
        Optional<Client> optionalCliente = clientRepository.findById(codigo);
        if (optionalCliente.isPresent()){ //cliente encontrado
            Client c = optionalCliente.get(); // extraindo a entity do Optional
            c.setAtivo(0);
            clientRepository.save(c);
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }


}
