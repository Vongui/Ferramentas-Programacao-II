import { inject, Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { UserService } from '../services/user.service';

@Injectable({
  providedIn: 'root',
})
export class GuardAdminGuard implements CanActivate {
  private router = inject(Router);
  private userService = inject(UserService);

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): MaybeAsync<GuardResult> {

    if (this.userService.isAdmin()) {
      return true;
    }

    return this.router.parseUrl('/pagina-publica');
  }
}
