package br.edu.ifsp.pep.bcc.controle.demo.service;

import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServico;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServicoId;
import br.edu.ifsp.pep.bcc.controle.demo.repository.ItemOrdemServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemOrdemServicoService {
    private final ItemOrdemServicoRepository itemOrdemServicoRepository;

    public List<ItemOrdemServico> findAll() {
        return itemOrdemServicoRepository.findAll();
    }

    public ItemOrdemServico findById(ItemOrdemServicoId id) {
        Optional<ItemOrdemServico> optional = itemOrdemServicoRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public ItemOrdemServico save(ItemOrdemServico item) {
        return itemOrdemServicoRepository.save(item);
    }

    public void deleteById(ItemOrdemServicoId id) {
        itemOrdemServicoRepository.deleteById(id);
    }
}
