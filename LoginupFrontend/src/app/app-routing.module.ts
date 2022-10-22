import { NormalGuard } from './services/normal.guard';
import { AdminGuard } from './services/admin.guard';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';
import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { HomeComponent } from './pages/home/home.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
/*
*PATH Y PATHMATCH:
path: es donde se indica el directorio.
si está vacío, este redirigirá al usuario al componente Home,
el cual visualiza el mensaje de bienvenida.
Y así sucesivamente dependiendo a qué componente hace referencia cada directorio.
Ej: si accede un usuario administrador, se redireccionará automáticamente
al componente de admin.
*/
const routes: Routes = [
  //Envía al componenete home
  {
    path : '',
    component : HomeComponent,
    pathMatch : 'full'
  },
  //Envía al componente de registro
  {
    path : 'signup',
    component : SignupComponent,
    pathMatch : 'full'
  },
  //Redirige al componente de login
  {
    path : 'login',
    component : LoginComponent,
    pathMatch : 'full'
  },
  //componentes de un usuario administrador
  {
    path:'admin',
    component:DashboardComponent,
    pathMatch:'full',
    canActivate:[AdminGuard]
  },
    //componentes de un usuario comun y corriente
  {
    path:'user-dashboard',
    component:UserDashboardComponent,
    pathMatch:'full',
    canActivate:[NormalGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
