import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {ClientService} from '../../services/client.service';
import {Client} from '../../modules/client.module';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-new-client',
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './new-client.html',
  styleUrl: './new-client.css',
})
export class NewClient {

  constructor(private ClientService:ClientService,private router: Router) {}
  newClient:Client= {};

  createClient(){
    this.ClientService.createClient(this.newClient).subscribe({
      next:()=>{

        this.router.navigate(['/nav/clients']);
        this.newClient={};


      },
      error:(err: any) => console.log(err)


    });
  }

}
