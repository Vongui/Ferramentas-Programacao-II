package br.edu.ifsp.pep.bcc.controle.demo.repository;

import br.edu.ifsp.pep.bcc.controle.demo.model.entities.Cliente;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer> {
    boolean existsByCliente(Cliente cliente);
}
