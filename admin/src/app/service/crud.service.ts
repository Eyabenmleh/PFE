import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Admin } from '../Entite/Admin.Entite';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class CrudService {
helper=new JwtHelperService()
apiUrl="http://localhost:8081/api"
loginUserUrl="http://localhost:8081/api/admin/login"
  constructor(private http:HttpClient) { }
  addadmin(admin:Admin){
    return this.http.post<any>(this.apiUrl+"/admin", admin);
  }

  getAdmin(): Observable<Admin[]>{
    return this.http.get<Admin[]>(this.apiUrl +"/admin");
  }

  onDeleteAdmin(id : number){
    const url =`${this.apiUrl+"/admin"}/${id}` 
    return this.http.delete(url )
  }

  loginAdmin(admin:Admin){
    return this.http.post<any>(this.loginUserUrl, admin);
  }

  findAdminById(id : number): Observable<Admin> {
    const url = `${this.apiUrl + "/admin"}/${id}`;
    return this.http.get<Admin>(url)
  }
  updateAdmin(id:number,admin: Admin) {
    const url = `${this.apiUrl+"/admin"}/${id}`
    return this.http.put<any>(url,admin);
  }

  userDetails(){
    let token:any=localStorage.getItem('myToken'); 
    let decodeToken= this.helper.decodeToken(token);
     return decodeToken.data;
   }
}
