package br.edu.ifsp.pep.bcc.estoque.controller;

import br.edu.ifsp.pep.bcc.estoque.model.entity.Part;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("part")
public class PartController {

    @GetMapping("/all")
    public List<Part> getParts(){
        Part part1 = new Part(1, "Peça 1", 212.5);
        Part part2 = new Part(2, "Peça 2", 42.4);
        Part part3 = new Part(3, "Peça 3", 324.6);
        Part part4 = new Part(4, "Peça 4", 232.67);

        List<Part> parts = new ArrayList<>();
        parts.add(part1);
        parts.add(part2);
        parts.add(part3);
        parts.add(part4);
        return parts;
    }

    @GetMapping(value = "/{num}", produces = "application/json")
    public Part getPart(@PathVariable int num){
        Part part = new Part(num, "Peça", 23.5);
        return part;
    }

}