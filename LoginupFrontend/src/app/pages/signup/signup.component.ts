import  Swal  from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public user = {
    username : '',
    password : '',
    nombre : '',
    apellido : '',
    email : '',
    telefono : ''
  }
//Implementa el método principal de Angular
  constructor(private userService:UserService,private snack:MatSnackBar) { }

  ngOnInit(): void {
  }

  formSubmit(){
    console.log(this.user);
    if(this.user.username == '' || this.user.username == null){
      this.snack.open('El nombre de usuario es obligatorio','Aceptar',{
        duration : 3000, //duración de 3000 milisegundos
        verticalPosition : 'top', //hacia arriba
        horizontalPosition : 'right' //hacia la derecha
      });
      return;
    }
//añade al usuario ingresado dentro del formulario de registro y se suscribe.
    this.userService.añadirUsuario(this.user).subscribe(
      /*
      ¿Para qué nos sirve la suscripción?
      El Observable representa un flujo de datos,
      una colección de eventos que se pueden emitir en algún momento futuro
      y el Observer son objetos que están escuchando el flujo de datos y actúan
      sobre los valores que éste emite.
      Entonces, la Suscripción representa la ejecución de un observable
       y también sirve para cancelar la ejecución en un momento dado.
       Entonces: va al api rest y nos retorna un observable. Siendo un observer, necesitamos suscribirnos a ese observable.
       Y a partir de ahí visualiza el mensaje correspondiente a si se guardó con éxito o si ocurrió un error.
      */
      (data) => {
        console.log(data);
        //se utiliza sweetalert2 para mostrar el logueo con exito. En vez de un alert se usa ventaqna emergente de swa2
        Swal.fire('Usuario guardado','Usuario registrado con exito en el sistema','success');
      },(error) => {
        console.log(error);
        /*
        Ventana de alerta en caso de un error implementando SnackBar perteneciente a AngularMaterial.
        https://material.angular.io/components/snack-bar/overview
        */
        this.snack.open('Ocurrió un error en el sistema','Aceptar',{
          duration : 3000
        });





        Swal.fire(
          'Cambio guardado',
          '',
          'success'
        )
      }
    )
  }
}
