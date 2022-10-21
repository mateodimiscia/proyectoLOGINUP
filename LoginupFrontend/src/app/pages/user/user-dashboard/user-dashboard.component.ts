import { LoginService } from './../../../services/login.service';
import { UserService } from './../../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { usuario } from 'src/app/interfaces/interfaces';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
usuarios:usuario;
  constructor(private UserServices:UserService, private loginService:LoginService ) {}

  ngOnInit(): void {
    this.obtenerusuarios();
  }

/*
obtengo los datos del usuario
*/
  private obtenerusuarios(){
    console.log(this.loginService.getUser().username);
    this.UserServices.obtenerdatosUsuario(this.loginService.getUser().username).subscribe((dato) => {
      this.usuarios = dato;
    });
  }
}
