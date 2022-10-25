import { LoginService } from './login.service';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree , Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NormalGuard implements CanActivate {

  constructor(private loginService:LoginService,private router:Router){

  }
/*
los GUARDS son de alguna manera: middlewares que se ejecutan antes de cargar
una ruta y determinan si se puede cargar dicha ruta o no
*/
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
       //si esta conectado el usuario y es de tipo normal retorna true
      if(this.loginService.isLoggedIn() && this.loginService.getUserRole() == 'NORMAL'){
        return true;
      }
//en caso de ser falso redirecciona al login
      this.router.navigate(['login']);
      return false;
  }

}
