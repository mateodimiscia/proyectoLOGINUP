import { LoginService } from './login.service';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

  constructor(private loginService:LoginService) { //Inyecta el LoginService

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req;
    const token = this.loginService.getToken(); // Constante que guarda el token
    if(token != null){ // Si el token no está vacío, clona el authReq
      authReq = authReq.clone({
        setHeaders : {Authorization: `Bearer ${token}` }
      })
    }
    return next.handle(authReq);
  }

}

export const authInterceptorProviders = [
  {
    provide : HTTP_INTERCEPTORS, /*Interceptor que se registra luego en el app.module.
    Los interceptors en Angular nos brindan un mecanismo para interceptar y/o mutar las solicitudes y respuestas http.*/
    useClass : AuthInterceptor,
    multi : true
  }
]
