import { AutenticacaoService } from './../service/autenticacao.service';
import { inject, Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UsuarioService } from '../services/usuario.service';

@Injectable()
export class AutenticacaoInterceptor implements HttpInterceptor {
  private usuarioService = inject(UsuarioService);
  private autenticacaoService = inject(AutenticacaoService);
  constructor() {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    const token = this.usuarioService.getToken();
    if (token) {
      request = request.clone({
        setHeaders: { Authorization: `Bearer ${token}`},
      });
    }

    const authToken = this.autenticacaoService.getAccessToken();
    if (authToken) {
      request = request.clone({
        setHeaders: { Authorization: `Bearer ${authToken}`},
      });
    }

    return next.handle(request);

  }
}

// let token = this.usuarioService.getToken();

//     if (!token) {
//       token = this.autenticacaoService.getAccessToken();
//     }

//     if (token) {
//       request = request.clone({
//         setHeaders: { Authorization: `Bearer ${token}` },
//       });
//     }

//     return next.handle(request);
//   }
