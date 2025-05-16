import {HttpEvent, HttpHandlerFn, HttpRequest} from '@angular/common/http';
import {OAuthService} from 'angular-oauth2-oidc';
import {inject} from '@angular/core';
import {Observable} from 'rxjs';

export function authInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>>  {
    const bearerToken = inject(OAuthService).getAccessToken();

    if (!req.headers.has('Authorization')) {
      req = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${bearerToken}`)
      });
    }
    return next(req);
}
