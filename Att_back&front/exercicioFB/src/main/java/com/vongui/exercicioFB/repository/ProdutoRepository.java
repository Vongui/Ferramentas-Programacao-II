package com.vongui.exercicioFB.repository;

import com.vongui.exercicioFB.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
