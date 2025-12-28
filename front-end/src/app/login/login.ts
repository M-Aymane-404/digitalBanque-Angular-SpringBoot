import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from '../services/auth.service';

import {Router} from '@angular/router';
import {UserModule} from '../modules/User.module';
import {NormalizedHttpError} from '../interceptors/error.interceptor';

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login implements OnInit{

  formLogin !: FormGroup;
  loginErrorMessage: string | null = null;
  user:UserModule = {
    username:"",
    password:"",
 };
  constructor(private fb:FormBuilder, private service:AuthService, private route:Router, private cd :ChangeDetectorRef) {
  }

  ngOnInit(): void {
    this.formLogin = this.fb.group({
      username: this.fb.control(""),
      password: this.fb.control("")
    })

  }
  handleLogin() {
    this.loginErrorMessage = null;
    const loginData = {
      username: this.formLogin.value.username,
      password: this.formLogin.value.password
    };

    console.log(loginData);



    this.service.login(loginData).subscribe({
      next: res =>{
        this.service.loadProfil(res);
          this.route.navigateByUrl("/nav/dashboard");

      }
      ,
      error: (err: NormalizedHttpError | any) => {
        if (err && typeof err.httpStatus === 'number' && err.httpStatus === 401) {
          this.loginErrorMessage = "Invalid username or password";
          this.cd.detectChanges();
          return;
        }
        this.loginErrorMessage = (err && typeof err.message === 'string' && err.message.trim().length > 0)
          ? err.message
          : 'An error has occurred. Please try again.';
        this.cd.detectChanges();
      }
    });
  }

}



