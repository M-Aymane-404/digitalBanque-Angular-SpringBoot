import { Component } from '@angular/core';
import {Router, RouterLink, RouterLinkActive, RouterOutlet} from '@angular/router';
import {AuthService} from '../services/auth.service';
import {UpperCasePipe} from '@angular/common';

@Component({
  selector: 'app-nav-bar',
  imports: [
    RouterLink,
    RouterOutlet,
    UpperCasePipe,
    RouterLinkActive
  ],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.css',
})
export class NavBar {
constructor(public authService:AuthService, private router:Router) {
}
  protected handleLogout() {
this.authService.logout();
  this.router.navigateByUrl("/login");

  }
}
