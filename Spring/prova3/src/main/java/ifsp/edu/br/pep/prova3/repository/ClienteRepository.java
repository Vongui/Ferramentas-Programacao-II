package ifsp.edu.br.pep.prova3.repository;

import ifsp.edu.br.pep.prova3.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByLogin(String login);
}
