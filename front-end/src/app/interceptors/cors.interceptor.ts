import {HttpEvent, HttpHandlerFn, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';

export function corsInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {

  const authReq = req.clone({
    headers: req.headers.set('Access-Control-Allow-Origin', '*')
  });
  console.log('CORS Interceptor');
  console.log(authReq);
  return next(req);
}
