package br.edu.ifsp.pep.bcc.controle.demo.service;

import br.edu.ifsp.pep.bcc.controle.demo.controller.exception.NotFoundException;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServico;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServicoId;
import br.edu.ifsp.pep.bcc.controle.demo.repository.ItemOrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemOrdemServicoService {
    private final ItemOrdemServicoRepository itemOrdemServicoRepository;

    public List<ItemOrdemServico> findAll() {
        return itemOrdemServicoRepository.findAll();
    }

    public ItemOrdemServico findById(ItemOrdemServicoId id) {
        return itemOrdemServicoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Item não encontrado!"));
    }

    public ItemOrdemServico create(ItemOrdemServico item) {
        return itemOrdemServicoRepository.save(item);
    }

    public ItemOrdemServico update(ItemOrdemServicoId id, ItemOrdemServico itemAtualizado) {
        if (!itemOrdemServicoRepository.existsById(id)) {
            throw new NotFoundException("Item não encontrado!");
        }
        itemAtualizado.setNumeroItem(id.getNumeroItem());
        itemAtualizado.setOrdemServico(itemAtualizado.getOrdemServico());
        return itemOrdemServicoRepository.save(itemAtualizado);
    }

    public void deleteById(ItemOrdemServicoId id) {
        if (!itemOrdemServicoRepository.existsById(id)) {
            throw new NotFoundException("Item não encontrado!");
        }
        itemOrdemServicoRepository.deleteById(id);
    }
}
