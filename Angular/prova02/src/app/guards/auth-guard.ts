import { inject, Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  GuardResult,
  MaybeAsync,
  Router,
  RouterStateSnapshot,
} from '@angular/router';
import { LoginService } from '../services/login-service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {

  private router = inject(Router);
  private loginService = inject(LoginService);
  

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): MaybeAsync<GuardResult> {
      console.log("chegou guard");
      console.log(this.loginService.getToken());
      
      
      if (this.loginService.getToken()) {
        console.log("trueguard");
        
          return true;
      }
      this.router.navigate(['/login']);
      console.log("falseguard");
      
      return false;
  }
}
