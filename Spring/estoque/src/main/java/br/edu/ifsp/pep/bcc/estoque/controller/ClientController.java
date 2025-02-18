package br.edu.ifsp.pep.bcc.estoque.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //usado para requisições em json
@RequestMapping("client") //todo request que estiver utilizando o endpoint especificado será "jogado" para essa classe
public class ClientController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World!!! ";
    }

    @GetMapping("/all")
    public String getTodos(){
        return "Joao<br/>Maria<br/>Gustavo<br/>";
    }

    @GetMapping("/{codigo}")
    public String getClient(@PathVariable int codigo){
        return "Cliente -> " + codigo;
    }

}
