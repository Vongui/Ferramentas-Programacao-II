package br.edu.ifsp.pep.bcc.controle.demo.controller;

import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServico;
import br.edu.ifsp.pep.bcc.controle.demo.model.entities.ItemOrdemServicoId;
import br.edu.ifsp.pep.bcc.controle.demo.service.ItemOrdemServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/item-ordem-servico")
@RequiredArgsConstructor
public class ItemOrdemServicoController {
    private final ItemOrdemServicoService itemOrdemServicoService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemOrdemServico> getAll() {

        return itemOrdemServicoService.findAll();
    }

    @GetMapping(value = "/{ordemServicoId}/{numeroItem}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemOrdemServico> getById(@PathVariable int ordemServicoId, @PathVariable int numeroItem) {
        ItemOrdemServicoId id = new ItemOrdemServicoId(ordemServicoId, numeroItem);
        ItemOrdemServico item = itemOrdemServicoService.findById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

//    @PostMapping(value = "",
//                consumes = MediaType.APPLICATION_JSON_VALUE,
//                produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public ItemOrdemServico create(@RequestBody ItemOrdemServico itemOrdemServico) {
//
//        ItemOrdemServico
//    }

//    @DeleteMapping("/{ordemServicoId}/{numeroItem}")
//    public ResponseEntity<Void> delete(@PathVariable int ordemServicoId, @PathVariable int numeroItem) {
//        ItemOrdemServicoId id = new ItemOrdemServicoId(ordemServicoId, numeroItem);
//        if (itemOrdemServicoService.findById(id).isPresent()) {
//            itemOrdemServicoService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
    }
