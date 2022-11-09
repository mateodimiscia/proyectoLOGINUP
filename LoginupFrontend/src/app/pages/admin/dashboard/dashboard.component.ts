import { Component, OnInit } from '@angular/core';
import { usuario } from 'src/app/interfaces/interfaces';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  usuarios:usuario[];
  usuario:usuario=new usuario();

  constructor(private UserServices:UserService) {}

  ngOnInit(): void {
    this.obtenerusuarios();
  }
//recorre el array de los usuarios y mediante un condicional verifica el estado.
//si el estado es Habilitado, indica un input para realizar la acción contraria.
  private obtenerusuarios(){
    this.UserServices.obtenerusuarios().subscribe((dato)=>{
      this.usuarios=dato;
      for(let i = 0 ;i<dato.length;i++){
        if (this.usuarios[i].enabled == true ){

          this.usuarios[i].estadoUser = "Habilitado";

        }else{
          this.usuarios[i].estadoUser = "Deshabilitado";
        }
      }
    })
  }

  modificarEstado(iduser:any,habil:string){
    if(habil == "Habilitado"){
      habil = "Deshabilitado";
    }else{
      habil = "Habilitado";
    }


    this.UserServices.modificarDato(iduser,habil).subscribe();
    //muestra un pop-up que indica los cambios de la habilitacion/deshabilitacion del usuario
    Swal.fire({
      position: 'center',
      icon: 'success',
      title: 'El estado del usuario fue modificado a: ' +habil,
      showConfirmButton: false,
      timer: 2000
    })
  };

}

