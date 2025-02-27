package br.edu.ifsp.pep.bcc.estoque.repository;

import br.edu.ifsp.pep.bcc.estoque.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNomeLike(String nome);


}
