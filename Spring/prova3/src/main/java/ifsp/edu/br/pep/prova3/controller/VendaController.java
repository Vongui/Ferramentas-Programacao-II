package ifsp.edu.br.pep.prova3.controller;

import ifsp.edu.br.pep.prova3.entity.ItemVenda;
import ifsp.edu.br.pep.prova3.entity.Produto;
import ifsp.edu.br.pep.prova3.entity.Venda;
import ifsp.edu.br.pep.prova3.repository.ProdutoRepository;
import ifsp.edu.br.pep.prova3.repository.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vendas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VendaController {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Venda>> listarTodas() {
        return ResponseEntity.ok(vendaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(
            @PathVariable Long id) {
        return vendaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> registrarVenda(
            @RequestBody Venda venda) {
        try {
            venda.setId(null);
            venda.setData(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());

            for (ItemVenda item : venda.getItens()) {
                item.setVenda(venda);

                Produto produtoBanco = produtoRepository.findById(item.getProduto().getId())
                        .orElseThrow(() -> new RuntimeException("Produto ID " + item.getProduto().getId() + " não existe."));

                if (produtoBanco.getQuantidade() < item.getQuantidade()) {
                    return ResponseEntity.badRequest().body("Estoque insuficiente para: " + produtoBanco.getDescricao());
                }

                produtoBanco.setQuantidade(produtoBanco.getQuantidade() - item.getQuantidade());
                produtoRepository.save(produtoBanco);

                item.setPreco(produtoBanco.getPreco());
            }

            Venda vendaConcluida = vendaRepository.save(venda);
            return ResponseEntity.status(HttpStatus.CREATED).body(vendaConcluida);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Falha ao registrar venda: " + e.getMessage());
        }
    }
}