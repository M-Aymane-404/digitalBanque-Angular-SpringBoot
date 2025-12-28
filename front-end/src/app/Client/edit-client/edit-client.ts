import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {ClientService} from '../../services/client.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {Client} from '../../modules/client.module';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-edit-client',
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './edit-client.html',
  styleUrl: './edit-client.css',
})
export class EditClient implements OnInit{
  clientToUpdated:Client={};





  constructor(private clientsService:ClientService, private router:Router,private route:ActivatedRoute, private cd: ChangeDetectorRef) {
  }


  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.clientsService.getClientById(id).subscribe({
        next: client => {
           this.clientToUpdated = client;
          this.cd.detectChanges()

        },
        error: err => {
          console.error('HTTP ERROR:', err);

        }
      });
    } else {
      this.router.navigate(["/nav/dashboard"]);
    }
  }

  UpdateClient() {

      this.clientsService.UpdateClient(this.clientToUpdated).subscribe({
        next: () => {
           this.router.navigate(["/nav/clients"]);
        },
        error: err => {
          console.log(err)
        }
      })
    }


  }
