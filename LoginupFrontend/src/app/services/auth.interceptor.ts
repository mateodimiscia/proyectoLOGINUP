import { LoginService } from './login.service';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
/*
¿QUÉ ES UN INTERCEPTOR? brindan un mecanismo para interceptar o mutar las peticiones
o solicitudes del http module. NO CONFUNDIR CON GUARDS
*/
@Injectable()
//se implementa metodo httpinterceptor
export class AuthInterceptor implements HttpInterceptor{

  constructor(private loginService:LoginService) { //Inyecta el LoginService

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let authReq = req;
    const token = this.loginService.getToken(); // Constante que guarda el token
    if(token != null){ // Si el token es diferente o nulo, clona el authReq
      authReq = authReq.clone({
        setHeaders : {Authorization: `Bearer ${token}` }//establecen cabeceras como se hace en postman mediante una peticion
      })
    }
    return next.handle(authReq);
  }
}
//se crea una constante
export const authInterceptorProviders = [
  {
    provide : HTTP_INTERCEPTORS, //Provider es cualquier cosa que puede crear o devolver un servicio. De hecho el nombre del provider es igual al de la clase
    useClass : AuthInterceptor,
    multi : true //permite agregar la cantidad de interceptores que queramos
  }
]
