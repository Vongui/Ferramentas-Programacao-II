import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProdutoService } from '../../services/produto-service';
import { Produto } from '../../models/produto.model';

import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { ButtonModule } from 'primeng/button';
import { Status } from '../../models/status.enum';
import { SelectButtonModule } from 'primeng/selectbutton';

@Component({
  selector: 'app-produto-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    InputNumberModule,
    SelectButtonModule,
    ButtonModule
  ],
  templateUrl: './produto-form.html'
})
export class ProdutoForm implements OnInit {
  private fb = inject(FormBuilder);
  private produtoService = inject(ProdutoService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  form!: FormGroup;
  isEdicao = false;

  statusOptions = [
    { label: 'Ativo', value: Status.ATIVO },
    { label: 'Inativo', value: Status.INATIVO },
  ];

  ngOnInit() {
    this.form = this.fb.group({
      codigo: [null],
      descricao: ['', Validators.required],
      preco: [null, Validators.required],
      quantidade: [null, Validators.required],
      status: [Status.ATIVO, Validators.required]
    });

    const codigoId = this.route.snapshot.paramMap.get('id');
    if (codigoId) {
      this.isEdicao = true;
      this.carregarProduto(Number(codigoId));
    }
  }

  carregarProduto(codigo: number) {
    this.produtoService.obterPorCodigo(codigo).subscribe({
      next: (produto) => {
        this.form.patchValue(produto);
      },
      error: (err) => console.error('Erro ao carregar produto', err)
    });
  }

  salvar() {
    if (this.form.invalid) return;

    const produtoData: Produto = this.form.value;

    if (this.isEdicao) {
      this.produtoService.atualizarProduto(produtoData).subscribe({
        next: () => this.voltar(),
        error: (err) => console.error('Erro ao atualizar', err)
      });
    } else {
      this.produtoService.criarProduto(produtoData).subscribe({
        next: () => this.voltar(),
        error: (err) => console.error('Erro ao criar', err)
      });
    }
  }

  voltar() {
    this.router.navigate(['/produtos']);
  }
}
