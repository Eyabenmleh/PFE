import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Admin } from '../Entite/Admin.Entite';
import { CrudService } from '../service/crud.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  messageCommande = ""
  loginForm: FormGroup
    constructor(
      private fb: FormBuilder,
      private service:CrudService,
      private router:Router
    ) { 
      let formControls = {
        email: new FormControl('',[
          Validators.required,
          Validators.email
          
        ]),
        mp: new FormControl('',[
          Validators.required,
         
        ])
      }
  
      this.loginForm = this.fb.group(formControls)
    }
  
    get email() { return this.loginForm.get('email') }
    get mp() { return this.loginForm.get('mp') }
   
    login() {
      let data = this.loginForm.value;
      console.log(data);
      let admin = new Admin(
       null,null,null,data.email,data.mp,null);
      console.log(admin);
      if (
    
        data.email == 0 ||
        data.mp == 0
      )
      {
        this.messageCommande=`<div class="alert alert-success" role="alert">
        remplir votre champ
      </div>`
      } else {
    
        this.service.loginAdmin(admin).subscribe(
          res=>{

            console.log(res);
            this.messageCommande=`<div class="alert alert-success" role="alert">
        avec success
      </div>`
            let token = res.token;
            localStorage.setItem("myToken",token);
            localStorage.setItem("role",res.role);
            this.router.navigate(['']).then(()=>{window.location.reload()});
          },
          err=>{
            console.log(err);
            this.messageCommande=`<div class="alert alert-success" role="alert">
            erreur
          </div>`
            
          }
        )
        
      }
      }

}
