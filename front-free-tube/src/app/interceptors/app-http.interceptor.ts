import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Injectable()
export class AppHttpInterceptor implements HttpInterceptor {

  accesToken!: string;
  constructor(private oidcSecurityService: OidcSecurityService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> { 
    if (!request.url.includes("auth/login")) { 
      this.oidcSecurityService.getAccessToken().subscribe(
        (token)=> {
          this.accesToken= token;
        }
      );
      let newRequest= request.clone({
        headers: request.headers.set('Authorization', 'Bearer '+this.accesToken)
      })
      return next.handle(newRequest).pipe(
        catchError(err=> {
          if(err.status== 401){
            this.oidcSecurityService.logoff
          }
          return throwError(err.message);
        })
      );
    } else return next.handle(request)
  }
}
