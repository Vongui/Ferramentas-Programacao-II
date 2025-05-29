package com.prova.api.repository;

import com.prova.api.model.Pacotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacotesRepository extends JpaRepository<Pacotes, Integer> {

    @Query(value = "SELECT * FROM pacotes WHERE cliente_id = :codigo", nativeQuery = true)
    List<Pacotes> findPacotesCliente(int codigo);
}
