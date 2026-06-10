package com.vongui.exercicioFB.controller;

import com.vongui.exercicioFB.model.Produto;
import com.vongui.exercicioFB.model.dto.ProdutoDTO;
import com.vongui.exercicioFB.model.mapper.ProdutoMapper;
import com.vongui.exercicioFB.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper mapper;

    @PutMapping("/{id}")
    public ResponseEntity<Produto> alterProduto(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoDTO produtoDTO
    ){
        return produtoRepository.findById(id)
                .map(p -> {
                    p.setDescricao(produtoDTO.descricao());
                    p.setQuantidade(produtoDTO.quantidade());
                    p.setPreco(produtoDTO.preco());
                    p.setStatus(produtoDTO.status());
                    return ResponseEntity.ok(produtoRepository.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(
            @PathVariable Long id
    ){
        return produtoRepository.findById(id)
                .map(p -> {
                    produtoRepository.delete(p);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Produto> createdProduto(
            @Valid @RequestBody ProdutoDTO produtoDTO
    ) {
        Produto novoProduto = produtoRepository.save(mapper.toProduto(produtoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(produtoRepository.findById(id).orElse(null));
    }
}
