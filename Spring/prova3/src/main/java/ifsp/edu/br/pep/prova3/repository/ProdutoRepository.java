package ifsp.edu.br.pep.prova3.repository;

import ifsp.edu.br.pep.prova3.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
