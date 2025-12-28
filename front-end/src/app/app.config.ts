import {ApplicationConfig, importProvidersFrom, provideBrowserGlobalErrorListeners} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
 import {provideHttpClient, withInterceptors, withInterceptorsFromDi} from '@angular/common/http';
import {appHttpInterceptor} from './interceptors/app-http-interceptor';
import {errorInterceptor} from './interceptors/error.interceptor';


export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideHttpClient(withInterceptors([appHttpInterceptor, errorInterceptor]))
  ]
};
