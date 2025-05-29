package com.prova.api.service;

import com.prova.api.controller.dto.PacotesDTO;
import com.prova.api.controller.dto.PacotesMapper;
import com.prova.api.model.Cliente;
import com.prova.api.model.Pacotes;
import com.prova.api.repository.ClienteRepository;
import com.prova.api.repository.PacotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacotesService {

    private final PacotesRepository pacotesRepository;
    private final ClienteRepository clienteRepository;
    private final PacotesMapper pacotesMapper;

    public Pacotes create(PacotesDTO pacotes) throws Exception {
        try {
            Optional<Cliente> c = clienteRepository.findById(pacotes.idCliente());
            if (c.isEmpty()){
                throw new Exception("Cliente não cadastrado!!");
            }
            Pacotes p = pacotesMapper.dtoToPacotes(pacotes);
            p.setCliente(c.get());
            p.setDataRetorno(pacotes.dataRetorno());
            p.setQuantPessoas(pacotes.quantPessoas());
            p.setQteDiarias(pacotes.qteDiarias());
            System.out.println(p);
            return pacotesRepository.save(p);
        }catch(Exception ex){
            throw new Exception(ex.getMessage());
        }
    }

    public List<PacotesDTO> getAll(){
        List<Pacotes> pacotes = pacotesRepository.findAll();
        if (pacotes != null){
            List<PacotesDTO> dtos = pacotesMapper.pacotesToDto(pacotes);
            return dtos;
        }
        return null;
    }

    public List<Pacotes> buscarPacoteID(int codigo){
        List<Pacotes> pacotes = pacotesRepository.findPacotesCliente(codigo);
        if (pacotes != null){
            return pacotes;
        }
        return null;
    }

    public Boolean verificarExistenciaPorId(int codigo){
        boolean pacotes = pacotesRepository.existsById(codigo);
        if (pacotes){
            return pacotes;
        }
        return null;
    }

    public boolean alterarStatus(int codigo, int status){
        Optional<Pacotes> pacote = pacotesRepository.findById(codigo);
        if (pacote.isPresent()){
            Pacotes p = pacote.get();
            p.setStatus(status);
            pacotesRepository.save(p);
            return true;
        }
        return false;
    }
}
