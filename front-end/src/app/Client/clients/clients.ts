import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Client} from '../../modules/client.module';
import {ClientService} from '../../services/client.service';
import {Router, RouterModule} from '@angular/router';
import {debounceTime, distinctUntilChanged, startWith, switchMap} from 'rxjs';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-clients',
  imports: [CommonModule, RouterModule, ReactiveFormsModule],

  templateUrl: './clients.html',
  standalone: true,

  styleUrl: './clients.css',
})
export class Clients implements OnInit{
  clients:Client[]=[];

  clientSelected!:Client;

  searchControl = new FormControl('');

    constructor(private clientService:ClientService, private router:Router , private cd:ChangeDetectorRef,public authService:AuthService) {
    }

  ngOnInit(): void {
      this.clientService.getAllClients().subscribe(clients => {
   this.clients = clients;
        if (clients.length > 0) {
          this.clientSelected = clients[0];
        }

        this.cd.detectChanges();

 });



 this.searchControl.valueChanges.pipe(
   startWith(''),
   debounceTime(400),
   distinctUntilChanged(),
   switchMap(value => {
     if (!value || value.trim() === '') {
       return this.clientService.getAllClients();
     }
     return this.clientService.getClientByKeyword(value.trim());
   })
 ).subscribe({
   next: clients => {this.clients = clients
     if (clients.length > 0) {
       this.clientSelected = clients[0];
     }
     this.cd.detectChanges();
   },
   error: err => console.error(err)
 });
}



slectedClient(client:Client){
   if (client.id) {
    this.clientService.getClientById(client.id).subscribe({
      next: resp => {


        this.clientSelected = resp;

        this.cd.detectChanges()
      },
      error: err => {
        console.log(err);
      }
    });
  }
}
    deleteClient(client: Client) {
      if (confirm('vous êtes sûr de supprimer ce client ?')) {
        this.clients = this.clients.filter(c => c.id !== client.id);

        this.clientService.deleteClientService(client).subscribe({
          error: err => console.error(err)
        });
      }
    }


  protected readonly Array = Array;
}




