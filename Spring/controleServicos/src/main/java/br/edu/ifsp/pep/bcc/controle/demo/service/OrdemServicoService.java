package br.edu.ifsp.pep.bcc.controle.demo.service;

import br.edu.ifsp.pep.bcc.controle.demo.controller.exception.NoContent;
import br.edu.ifsp.pep.bcc.controle.demo.controller.exception.NotFoundException;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.Cliente;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.OrdemServico;
import br.edu.ifsp.pep.bcc.controle.demo.repository.OrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;

    public List<OrdemServico> findAll() throws NoContent{
        List<OrdemServico> ordens = ordemServicoRepository.findAll();
        if (ordens.isEmpty()) {
            throw new NoContent("Lista de ordens de servico vazia!!");
        }
        return ordens;
    }

    public OrdemServico findById(Integer id) {
        Optional<OrdemServico> ordemOptional = ordemServicoRepository.findById(id);

        if (ordemOptional.isPresent()) {
            return ordemOptional.get();
        }
        return null;
    }

    public OrdemServico create(OrdemServico ordemServico) {
        return ordemServicoRepository.save(ordemServico);
    }

    public void delete(int id) {
        ordemServicoRepository.deleteById(id);
    }

    public OrdemServico update(Integer id, OrdemServico ordemServicoAtualizada) {
        OrdemServico ordemExistente = findById(id);
        if (ordemExistente == null) {
            throw new NoContent("Ordem de serviço não encontrada para atualização!");
        }
        ordemServicoAtualizada.setNumero(id); // Garante que o ID não será alterado
        return ordemServicoRepository.save(ordemServicoAtualizada);
    }
}
