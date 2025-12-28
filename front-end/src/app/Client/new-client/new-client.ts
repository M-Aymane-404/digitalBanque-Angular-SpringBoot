import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ClientService } from '../../services/client.service';
import { Client } from '../../modules/client.module';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-new-client',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './new-client.html',
  styleUrl: './new-client.css',
})
export class NewClient {

  constructor(private clientService: ClientService, private router: Router) {}

  newClient: Client = {};

  createClient(form: NgForm) {
    if (form.invalid) {
      return;
    }

    this.clientService.createClient(this.newClient).subscribe({
      next: () => {
        this.router.navigate(['/nav/clients']);
        this.newClient = {};
        form.resetForm();
      },
      error: err => console.log(err)
    });
  }
}
