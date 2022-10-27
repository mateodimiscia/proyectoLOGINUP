import { Component, OnInit } from '@angular/core';
import { usuario } from 'src/app/interfaces/interfaces';
import { UserService } from 'src/app/services/user.service';


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

  private obtenerusuarios(){
    this.UserServices.obtenerusuarios().subscribe((dato)=>{
      this.usuarios=dato;
      for(let i = 0 ;i<dato.length;i++){
        if (this.usuarios[i].enabled == true ){

          this.usuarios[i].estadoUser = "Habilitado";

        }else{
          this.usuarios[i].estadoUser = "Deshabilitado";
        }
        console.log(this.usuarios[i]);
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
    alert("Se ha "+habil+ " su usuario correctamente")
  };

}

