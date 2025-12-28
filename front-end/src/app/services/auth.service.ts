import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {BanqueAccountModule} from '../modules/banqueAccount.module';
import {UserModule} from '../modules/User.module';

import {jwtDecode} from 'jwt-decode';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  idAuth:boolean=false;
  roles:any;
  username:any;
  accessTocken:any;

  constructor(private http: HttpClient, private router:Router) {}

  login(user: UserModule) {
    let options={
      headers:new HttpHeaders().set("Content-Type","application/x-www-form-urlencoded")
    };
    let parameters= new HttpParams().set("username",user.username)
      .set("password",user.password)
    return this.http.post("http://localhost:8080/auth/login",parameters,options)

  }
  loadProfil(data:any){
    this.idAuth= true;
      this.accessTocken=data['access_token'];
      let jwt:any = jwtDecode(this.accessTocken);
      this.username=  jwt.sub;
      this.roles = jwt.scope;
      window.localStorage.setItem("jwt-token", this.accessTocken);


  }

  logout() {
    this.idAuth= false;
    this.accessTocken=undefined;
     this.username=  undefined;
    this.roles = undefined;
    window.localStorage.removeItem("jwt-token");


  }

  loadjwtFromLocalStorage() {
    let token = window.localStorage.getItem("jwt-token");

    if(token){
      this.loadProfil({"access_token":token});
      this.router.navigateByUrl("/nav/dashboard")
    }
  }
}
