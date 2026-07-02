package br.edu.ifsp.pep.auth.repository;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.pep.auth.entity.Chamado;
import java.util.List;
import br.edu.ifsp.pep.auth.entity.Prioridade;


public interface ChamadoRepository extends JpaRepository<Chamado, Long>{

    List<Chamado> findByPrioridade(Prioridade prioridade);

}
