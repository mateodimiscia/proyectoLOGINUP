import  Swal  from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';

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
  constructor(private userService:UserService,private snack:MatSnackBar) {
   }
//Implementa el método principal de Angular
  ngOnInit(): void {
  }

  formSubmit(){
    this.verificarCampos(this.user.username,this.user.password,this.user.nombre,this.user.apellido,this.user.email,this.user.telefono)
    }
    verificarCampos(username:String,password:String,nombre:String,apellido:String,email:String,telefono:String){
    let mensaje: string= "Faltan campos por llenar";
      if(username == '' || username == null){
        this.snack.open(mensaje,'Aceptar',{
          duration : 3000, //duración de 3000 milisegundos
          verticalPosition : 'top', //hacia arriba
          horizontalPosition : 'right' //hacia la derecha
        })
      }else if(apellido== '' || apellido == null) {
        this.snack.open(mensaje,'Aceptar',{
          duration : 3000, //duración de 3000 milisegundos
          verticalPosition : 'top', //hacia arriba
          horizontalPosition : 'right' //hacia la derecha
        })
      }else if(email== '' || email == null){
        this.snack.open(mensaje,'Aceptar',{
          duration : 3000, //duración de 3000 milisegundos
          verticalPosition : 'top', //hacia arriba
          horizontalPosition : 'right' //hacia la derecha
      })
      }else if(nombre== '' || nombre == null){
        this.snack.open(mensaje,'Aceptar',{
          duration : 3000, //duración de 3000 milisegundos
          verticalPosition : 'top', //hacia arriba
          horizontalPosition : 'right' //hacia la derecha
      })
      }else if(password== '' || password == null){
        this.snack.open(mensaje,'Aceptar',{
          duration : 3000, //duración de 3000 milisegundos
          verticalPosition : 'top', //hacia arriba
          horizontalPosition : 'right' //hacia la derecha
      })
    }else if(telefono == '' || telefono == null){
        this.snack.open(mensaje,'Aceptar',{
          duration : 3000, //duración de 3000 milisegundos
          verticalPosition : 'top', //hacia arriba
          horizontalPosition : 'right' //hacia la derecha
      })}else{
//añade al usuario ingresado dentro del formulario de registro y se suscribe.
        this.userService.añadirUsuario(this.user).subscribe();
        Swal.fire('Usuario guardado','Usuario registrado con exito en el sistema','success');
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
      }
    }
  }


