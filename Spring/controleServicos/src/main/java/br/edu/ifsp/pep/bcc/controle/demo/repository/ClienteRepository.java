package br.edu.ifsp.pep.bcc.controle.demo.repository;

import br.edu.ifsp.pep.bcc.controle.demo.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);

    Cliente findOneByEmail(String email);

    Cliente findOneByTelefone(String telefone);

}
