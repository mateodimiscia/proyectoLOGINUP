import { Router } from '@angular/router';
import { LoginService } from './../../services/login.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = {
    "username" : '',
    "password" : '',
  }

  constructor(private snack:MatSnackBar,private loginService:LoginService,private router:Router) { }

  ngOnInit(): void {
  }

  //Las acciones que se ejecutan al darle el boton de Iniciar Sesión
  formSubmit(){
    //En caso de que el campo username esté vacío o nulo
    if(this.loginData.username.trim() == '' || this.loginData.username.trim() == null){
      //Mensaje de error snackbar
      this.snack.open('El nombre de usuario es requerido','Aceptar',{
        //Con una duración de 3 segundos.
        duration:3000
      })
      return;
    }
// En caso de que falte el nombre de usuario
    if(this.loginData.password.trim() == '' || this.loginData.password.trim() == null){
      this.snack.open('La contraseña es requerida','Aceptar',{
        duration:3000
      })
      return;
    }
// En caso de que falte la contraseña
    this.loginService.generateToken(this.loginData).subscribe( //Da el alta al usuario
      (data:any) => {

        //console.log(data);
        //loginuser establece en el localstorage el token por eso le pasamos el token que estamos obteniendo
        this.loginService.loginUser(data.token);
        //vamos a obtener el actual usuario que está iniciado y se le pasa el user de tipo any
        this.loginService.getCurrentUser().subscribe((user:any) => {
          this.loginService.setUser(user);
          //se imprime el usuario por consola
          //console.log(user);

          //Las distintas funciones de los roles
          if(this.loginService.getUserRole() == 'ADMIN'){ //Usuario con privilegios de ADMINISTRADOR
          //si el usuario es administrador mostrará el dashboard de los administradores *AREA NO DESARROLLADA AÚN*
            this.router.navigate(['admin']);
            this.loginService.loginStatusSubjec.next(true);
          }
          else if(this.loginService.getUserRole() == 'NORMAL'){ //Usuario sin privilegios de un ADMINISTRADOR
       //si el usuario es NORMAL mostrará el dashboard de los usuarios predeterminados
            this.router.navigate(['user-dashboard']);
            this.loginService.loginStatusSubjec.next(true);
          }
          else{
            this.loginService.logout(); //Cierre de sesión
          }
        })
      },(error) => {
       // console.log(error);
        this.snack.open('Credenciales inválidas/inhabilitadas','Aceptar',{ // No coinciden los datos ingresados con los registrados
          duration:3000//duración de 3 segundos
        })
      }
    )
  }
}
