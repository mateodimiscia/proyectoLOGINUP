import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import baserUrl from './helper';
import { usuario } from '../interfaces/interfaces';

/*

*/
@Injectable({
  providedIn: 'root'
})
export class UserService {


    constructor(private httpClient: HttpClient) { }

    //se pasa un usuario de tipo any y se esta retornando a la url
    public añadirUsuario(user:any){
      return this.httpClient.post(`${baserUrl}/usuarios/`,user);
    }
    public obtenerusuarios():Observable<usuario[]> {
      return this.httpClient.get<usuario[]>(`${baserUrl}/usuarios/todos`);
    }
    //obtengo los datos del usuario
    public obtenerdatosUsuario(user:any):Observable<usuario> {
      return this.httpClient.get<usuario>(`${baserUrl}/usuarios/${user}`);
    }
}
