import { HttpInterceptorFn } from '@angular/common/http';
import {inject} from '@angular/core';
import {AuthService} from '../services/auth.service';

export const appHttpInterceptor: HttpInterceptorFn = (req, next) => {

  const authService = inject(AuthService);
  if(!req.url.includes("/auth/login")){
    let newReq= req.clone({
      headers:req.headers.set(
      "Authorization","Bearer " +authService.accessTocken
      )
    });
    return next(newReq);
  }
  return next(req);
};
