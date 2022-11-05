import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baserUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubjec = new Subject<boolean>();

  constructor(private http:HttpClient) { }

  //generamos el token
  //realiza peticion post con los datos del usuario para generar el token
  public generateToken(loginData:any){
    //simbolo $ valida una variable
    return this.http.post(`${baserUrl}/generate-token`,loginData);
  }

  public getCurrentUser(){
    return this.http.get(`${baserUrl}/actual-usuario`);
  }

  //iniciamos sesión y establecemos el token en el localStorage que se almacene cierto tiempo que es cuando caduca el token
  public loginUser(token:any){
    localStorage.setItem('token',token);
    return true;
  }

  //sirve para comprobar si estoy conectado o no en la cuenta
  public isLoggedIn(){
    //se crea variable  tokenStr
    let tokenStr = localStorage.getItem('token');
    //si ese token no está definido o es vacío/nulo se retorna false, es decir no está coectado
    if(tokenStr == undefined || tokenStr == '' || tokenStr == null){
      return false;
      //de lo contrario está iniciado. Sigue el token en el localStorage
    }else{
      return true;
    }
  }

  //cerramos sesion y eliminamos el token del localStorage
  public logout(){
    //el elemento que esté guardado como token será removido
    localStorage.removeItem('token');
    //otro elemento en el localStorage es el usuario
    localStorage.removeItem('user');
    return true;
  }

  //obtenemos el token
  public getToken(){
    return localStorage.getItem('token');
  }
//se obtiene el objeto usuario del localstorage
//se establece el usuario
  public setUser(user:any){
    //stringify convierte un valor de javascript a json
    localStorage.setItem('user', JSON.stringify(user));
  }
//si ese usuario es diferente o nulo se retorna, de lo contrario se cierra sesion y retorna un null (que no existe el usuario)
  public getUser(){
    let userStr = localStorage.getItem('user');
    if(userStr != null){
      return JSON.parse(userStr);
    }else{
      this.logout();
      return null;
    }
  }
//del usuario se retorna el rol o roles correspondientes
  public getUserRole(){
    let user = this.getUser();
    //se retorna la autoridad
    return user.authorities[0].authority;
  }

}
