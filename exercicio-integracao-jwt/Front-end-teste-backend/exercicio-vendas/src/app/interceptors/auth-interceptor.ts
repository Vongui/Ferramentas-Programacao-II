import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { UserService } from '../services/user.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(UserService);

  const token = authService.getToken();

  if (token) {
    const requisicaoClonada = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });

    return next(requisicaoClonada);
  }

  return next(req);
};
