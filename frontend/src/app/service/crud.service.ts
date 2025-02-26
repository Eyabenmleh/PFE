import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Client } from '../Entite/Client.Entite';

@Injectable({
  providedIn: 'root'
})
export class CrudService {
apiUrl="http://localhost:8081/api"
loginUserUrl="http://localhost:8081/api/admin/login"
  constructor(private http:HttpClient) { }
  addclient(client:Client){
    return this.http.post<any>(this.apiUrl+"/client", client);
  }

 

}
