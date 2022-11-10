import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn = false;
  user:any = null;

  constructor(public login:LoginService) { }

  ngOnInit(): void { //si la sesión del usuario está activa, visualiza el nombre del mismo.
    this.isLoggedIn = this.login.isLoggedIn();
    this.user = this.login.getUser();
    this.login.loginStatusSubjec.asObservable().subscribe(
      data => {
        this.isLoggedIn = this.login.isLoggedIn();
        this.user = this.login.getUser();
      }
    )
  }

//Es el metodo para cerrar sesión
//remueve del localstorage el token y el usuario guardado
  public logout(){
    Swal.fire({
      title: '¿Desea cerrar sesión?',
      showDenyButton: true,
      confirmButtonText: 'Salir',
      denyButtonText: `No salir`,
    }).then((result) => {
     //si el usuario eligió si se cierra sesión
      if (result.isConfirmed) {
        Swal.fire('Sesión cerrada!', '', 'success')
        this.login.logout();
        window.location.reload();
        //si el usuario elige NO cerrar sesión
      } else if (result.isDenied) {
        Swal.fire('Los cambios fueron descartados', '', 'error')
      }
    })
  }
}
