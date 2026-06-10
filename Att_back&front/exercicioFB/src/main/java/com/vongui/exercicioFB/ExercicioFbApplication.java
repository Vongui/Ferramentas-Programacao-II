package com.vongui.exercicioFB;

import com.vongui.exercicioFB.model.Produto;
import com.vongui.exercicioFB.model.Status;
import com.vongui.exercicioFB.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class ExercicioFbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExercicioFbApplication.class, args);
	}
	@Bean
	CommandLineRunner initDatabase(ProdutoRepository produtoRepository) {
		return args -> {

			Produto p1 = new Produto();
			p1.setDescricao("Notebook Dell Inspiron");
			p1.setPreco(new BigDecimal("3500.00"));
			p1.setQuantidade(10);
			p1.setStatus(Status.ATIVO);

			Produto p2 = new Produto();
			p2.setDescricao("Mouse Gamer Logitech");
			p2.setPreco(new BigDecimal("199.90"));
			p2.setQuantidade(25);
			p2.setStatus(Status.ATIVO);

			produtoRepository.save(p1);
			produtoRepository.save(p2);
		};
	}

}
