package com.prova.api.service;

import com.prova.api.controller.dto.ClienteDTO;
import com.prova.api.controller.dto.ClienteMapper;
import com.prova.api.model.Cliente;
import com.prova.api.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper mapper;

    public List<ClienteDTO> buscarPorNome(String nome){
        List<Cliente> listaCliente = clienteRepository.findByNomeLike("%"+nome+"%");
        if (listaCliente.isEmpty()){
            return null;
        }
        return mapper.listClienteToDto(listaCliente);
    }

    public Cliente buscarPorId(int codigo){
        Optional<Cliente> c = clienteRepository.findById(codigo);
        if (c.isEmpty()){
            return null;
        }
        return c.get();
    }


}
