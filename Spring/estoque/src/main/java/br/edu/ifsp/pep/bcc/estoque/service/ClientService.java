package br.edu.ifsp.pep.bcc.estoque.service;

import br.edu.ifsp.pep.bcc.estoque.model.entities.Client;
import br.edu.ifsp.pep.bcc.estoque.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client create(Client client){
        return clientRepository.save(client);
    }

    public boolean alterStatus(int codigo, int status){
        Optional<Client> optionalCliente = clientRepository.findById(codigo);
        if (optionalCliente.isPresent()){ //cliente encontrado
            Client c = optionalCliente.get(); // extraindo a entity do Optional
            c.setAtivo(status);
            clientRepository.save(c);
            return true;
        }
        return false;
    }

    public Client delete(int codigo) {
        Optional<Client> cliente = clientRepository.findById(codigo);
        if (cliente.isPresent()) {
            clientRepository.delete(cliente.get());
            return cliente.get();
        }
        return null;
    }

    public List<Client> getAll(){
        List<Client> listaClientes = clientRepository.findAll();
        if (listaClientes.isEmpty()){
            return null;
        }
        return listaClientes;
    }

    public Client getClientById(int codigo){
        Optional<Client> optionalClient = clientRepository.findById(codigo);
        if (optionalClient.isPresent()){
            return optionalClient.get();
        }
        return null;
    }

    public List<Client> getClientByNome(String nome){
        List<Client> listaCliente = clientRepository.findByNomeLike("%"+nome+"%");
        if (listaCliente.isEmpty()){
            return null;
        }
        return listaCliente;
    }

    public Client updateClient(int codigo, Client client){
        Optional<Client> optionalClient = clientRepository.findById(client.getCodigo());

        if (optionalClient.isPresent()){
            client.setCodigo(codigo);
            Client clientAlter = clientRepository.save(client);
            return clientAlter;
        }

        return null;
    }

}
