import {Component, OnInit, signal} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavBar} from './nav-bar/nav-bar';
import {AuthService} from './services/auth.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})


export class App implements OnInit{
  protected readonly title = signal('front-end');

  constructor(private authService:AuthService) {
  }

  ngOnInit(): void {
       this.authService.loadjwtFromLocalStorage();

    }
}
