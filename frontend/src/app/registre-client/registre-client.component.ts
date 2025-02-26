import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CrudService } from '../service/crud.service';
import { Client } from '../Entite/Client.Entite';

@Component({
  selector: 'app-registre-client',
  templateUrl: './registre-client.component.html',
  styleUrls: ['./registre-client.component.css']
})
export class RegistreClientComponent {
  messageCommande=""
  clientForm:FormGroup
  
  userFile: any;
  public imagePath: any;
  emailURL: any
  newProduit=new Client()
  public message!: string;
  constructor(private services : CrudService , private router : Router,private fb:FormBuilder) {
    let formControls = {
      nom: new FormControl('',[
        Validators.required,
      Validators.minLength(4)]),

        prenom: new FormControl('',[
          Validators.required,]),
    
      email: new FormControl('',[
        Validators.required,
      Validators.email]),

      mp: new FormControl('',[
        Validators.required,]),

     telephone: new FormControl('',[
          Validators.required,]),

     age: new FormControl('',[
      Validators.required,]),

    etat: new FormControl('',[
      Validators.required,
    Validators.minLength(4)]),
   }}
  get nom() {return this.clientForm.get('nom');} 
  get prenom() {return this.clientForm.get('prenom');} 
  get email() { return this.clientForm.get('email');}
  get mp() {return this.clientForm.get('mp');}
  get telephone() {return this.clientForm.get('telephone');}
  get age() {return this.clientForm.get('age');}
  get etat() {return this.clientForm.get('etat');}

  
   addNewClient() {
    let data = this.clientForm.value;
    console.log(data);
    let client = new Client(
     undefined, data.nom,data.prenom,data.email,data.mp,data.telephone,data.age,data.etat);
    console.log(client);
    
    if (
      data.nom == 0 ||
      data.prenom == 0 ||
      data.email == 0||
      data.mp == 0||
      data.telephone == 0||
      data.age == 0||
      data.etat == 0

    ) {
      this.messageCommande=`<div class="alert alert-danger" role="alert">
      remplir votre champ 
    </div>`
    
    } else {
    this.services.addclient(client).subscribe(
      res=>{
        console.log(res);
        this.messageCommande=`<div class="alert alert-success" role="alert">
        avec success
      </div>`
        
        this.router.navigate(['/listclient']).then(()=>{window.location.reload()})
        ;
      },
       err=>{
        this.messageCommande=`<div class="alert alert-warning" role="alert">
        EMAIL EXISTE deja!!!! 
      </div>`
  
      })
      setTimeout(() => {
        this.messageCommande=""
      }, 3000);
    
    }
  }



  ngOnInit(): void {
  }

}
