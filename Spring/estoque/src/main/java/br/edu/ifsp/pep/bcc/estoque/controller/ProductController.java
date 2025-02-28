package br.edu.ifsp.pep.bcc.estoque.controller;


import br.edu.ifsp.pep.bcc.estoque.model.entities.Product;
import br.edu.ifsp.pep.bcc.estoque.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getByDescricao(@RequestParam(value = "descricao", defaultValue = "") String descricao){
        return productRepository.findByDescricaoLike("%"+descricao+"%");
    }

}
