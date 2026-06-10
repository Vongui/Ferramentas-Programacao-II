package com.vongui.exercicioFB.model.mapper;

import com.vongui.exercicioFB.model.Produto;
import com.vongui.exercicioFB.model.dto.ProdutoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDTO toDTO(Produto produto);

    Produto toProduto(ProdutoDTO produtoDTO);
}
