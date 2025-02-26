import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Admin } from '../Entite/Admin.Entite';
import { CrudService } from '../service/crud.service';

@Component({
  selector: 'app-listadmin',
  templateUrl: './listadmin.component.html',
  styleUrls: ['./listadmin.component.css']
})
export class ListadminComponent {
  role:string;
  listeAdmin:Admin[]
  constructor(private servive:CrudService,private router:Router){}
  ngOnInit():void{
    this.role=localStorage.getItem("role") as string;
    this.servive.getAdmin().subscribe(admin=>{
      this.listeAdmin=admin})
  }
  DeleteAdmin(admin: Admin){
    if(confirm("Voulez vous supprimer ce message de admin avec l'ID " +admin.id+ "?")) {
       this.servive.onDeleteAdmin(admin.id).subscribe(() => {
        this.router.navigate(['/listadmin']).then(() => {window.location.reload()})})}
}

}
