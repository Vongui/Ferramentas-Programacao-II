package br.edu.ifsp.pep.bcc.controle.demo.repository;

import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServico;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServicoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrdemServicoRepository extends JpaRepository<ItemOrdemServico, ItemOrdemServicoId> {
}
