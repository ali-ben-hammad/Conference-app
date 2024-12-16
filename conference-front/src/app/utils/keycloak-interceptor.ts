import {inject, Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest} from '@angular/common/http';
import {from, mergeMap, Observable} from 'rxjs';
import {KeycloakService} from 'keycloak-angular';

export const keycloakInterceptor: HttpInterceptorFn = (req, next) => {
  const keycloakService = inject(KeycloakService);

  return from(keycloakService.getToken()).pipe(
    mergeMap((token) => {
      if (token) {
        const authReq = req.clone({
          setHeaders: {
            Authorization: `Bearer ${token}`,
          },
        });
        return next(authReq);
      }
      return next(req);
    })
  );
};
