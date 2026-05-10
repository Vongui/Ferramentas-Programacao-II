import { Component, inject } from '@angular/core';
import { UsuarioService } from '../../services/usuario-service';
import { Usuario } from '../../models/usuario-model';

@Component({
  selector: 'app-user-list',
  imports: [],
  templateUrl: './user-list.html',
  styleUrl: './user-list.scss',
})
export class UserList {
  private usuarioService = inject(UsuarioService);
  protected users: Usuario[] = [];

  ngOnInit() {
    this.usuarioService.getAll()
      .subscribe({
        next: (r) => {
          this.users = r
        },
        error: (err) => {
          console.log(err);
        }
      });
  }

}
