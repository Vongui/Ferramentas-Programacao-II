package br.edu.ifsp.pep.bcc.estoque.repository;

import br.edu.ifsp.pep.bcc.estoque.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByDescricaoLike(String descricao);

}
