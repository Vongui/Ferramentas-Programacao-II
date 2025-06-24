import { UsuarioService } from './../services/usuario.service';
import { inject, Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  private usuarioService = inject(UsuarioService);
  private router = inject(Router);


  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): MaybeAsync<GuardResult> {

      if (this.usuarioService.isLogged()){
        return true;
      }

      this.router.navigate(['/sessions/cpf']);
      return false;
  }

}
