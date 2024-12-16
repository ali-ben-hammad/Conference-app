import {
  APP_INITIALIZER,
  ApplicationConfig,
  inject,
  provideZoneChangeDetection
} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptors} from '@angular/common/http';
import {initializeKeycloak} from './utils/keycloak-init';
import {KeycloakService} from 'keycloak-angular';
import {keycloakInterceptor} from './utils/keycloak-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([
        keycloakInterceptor
      ])
    ),
    KeycloakService,
     {
       provide : APP_INITIALIZER,
       useFactory : initializeKeycloak,
       multi :true,
       deps : [KeycloakService]
     }
  ]
};
