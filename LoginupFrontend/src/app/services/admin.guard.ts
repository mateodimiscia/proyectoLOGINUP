import { LoginService } from './login.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private loginService:LoginService,private router:Router){

  }
/*
los GUARDS son de alguna manera: middlewares (agentes intermedios)
que ofrece servicios y funciones comunes para las aplicaciones
que se ejecutan antes de cargar
una ruta y determinan si se puede cargar dicha ruta o no
*/
  canActivate(
    route: ActivatedRouteSnapshot,//Contiene la información sobre una ruta asociada a un componente
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {//Representa el estado del enrutador en un momento en el tiempo.
      //si esta conectado el usuario y es admin retorna true
    if(this.loginService.isLoggedIn() && this.loginService.getUserRole() == 'ADMIN'){
      return true;
    }
//en caso de ser falso redirecciona al login
    this.router.navigate(['login']);
    return false;
  }

}
