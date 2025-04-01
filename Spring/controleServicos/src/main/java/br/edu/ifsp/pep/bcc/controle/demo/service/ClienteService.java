package br.edu.ifsp.pep.bcc.controle.demo.service;

import br.edu.ifsp.pep.bcc.controle.demo.controller.exception.NotFoundException;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.Cliente;
import br.edu.ifsp.pep.bcc.controle.demo.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente create(Cliente client) throws Exception {
        try {
            if ( nonNull(clienteRepository.findOneByEmail(client.getEmail()))){
                throw new Exception("Email já pertence a outro usuário!!");
            }
            if ( nonNull(clienteRepository.findOneByTelefone(client.getTelefone()))){
                throw new Exception("Telefone já pertence a outro usuário!!");
            }
            return clienteRepository.save(client);
        }catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public boolean alterStatus(int codigo, int status){
        Optional<Cliente> optionalCliente = clienteRepository.findById(codigo);
        if (optionalCliente.isPresent()){
            Cliente c = optionalCliente.get();
            c.setAtivo(status);
            clienteRepository.save(c);
            return true;
        }
        return false;
    }

    public Cliente delete(int codigo) {
        Optional<Cliente> cliente = clienteRepository.findById(codigo);
        if (cliente.isPresent()) {
            clienteRepository.delete(cliente.get());
            return cliente.get();
        }
        return null;
    }

    public List<Cliente> getAll(){
        List<Cliente> listaClientes = clienteRepository.findAll();
        if (listaClientes.isEmpty()){
            return null;
        }
        return listaClientes;
    }

    public Cliente getClientById(int codigo){
        Optional<Cliente> optionalClient = clienteRepository.findById(codigo);
        if (optionalClient.isPresent()){
            return optionalClient.get();
        }
        return null;
    }

    public List<Cliente> getClientByNome(String nome){
        List<Cliente> listaCliente = clienteRepository.findByNomeLike("%"+nome+"%");
        if (listaCliente.isEmpty()){
            return null;
        }
        return listaCliente;
    }

    public Cliente updateClient(int codigo, Cliente client){
        Cliente optionalClient = clienteRepository.findById(client.getCodigo()).
                orElseThrow(() -> new NotFoundException("Cliente não encontrado!!"));

        client.setCodigo(codigo);
        return clienteRepository.save(client);
    }

}
