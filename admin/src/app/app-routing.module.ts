import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AjouteradminComponent } from './ajouteradmin/ajouteradmin.component';
import { ListadminComponent } from './listadmin/listadmin.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ModifieradminComponent } from './modifieradmin/modifieradmin.component';

const routes: Routes = [
  {path:'',component:AjouteradminComponent},
  {path:'listadmin',component:ListadminComponent},
  {path:'login',component:LoginComponent},
  {path:'home',component:HomeComponent},
  {path:'modifieradmin/:id',component:ModifieradminComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
