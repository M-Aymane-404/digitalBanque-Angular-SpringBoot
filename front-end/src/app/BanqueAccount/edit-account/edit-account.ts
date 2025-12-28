import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ClientService} from '../../services/client.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BanqueAccountService} from '../../services/banque-account.service';
import {Client} from '../../modules/client.module';
import {BanqueAccountModule} from '../../modules/banqueAccount.module';
import {NgClass} from '@angular/common';

@Component({
  selector: 'app-edit-account',
  imports: [
    ReactiveFormsModule,
    FormsModule,
    NgClass
  ],
  templateUrl: './edit-account.html',
  styleUrl: './edit-account.css',
})
export class EditAccount implements OnInit{
  accountToUpdated: BanqueAccountModule = {
        type: 'CC',
        solde: 0,
        devise: 'MAD',
        decouvert: 0,
        tauxInteret: 0
      };

  accountToSUSPENDED: BanqueAccountModule = {
    type: 'CC',
    statut:'SUSPENDED'

  };





  constructor(private banqueAccountService:BanqueAccountService, private router:Router,private route:ActivatedRoute, private cd: ChangeDetectorRef) {
  }
  ngOnInit(): void {

  const id = Number(this.route.snapshot.paramMap.get('id'));
  if (id) {
    this.banqueAccountService.getBanqueAccountById(id).subscribe({
      next: resp => {
        this.accountToUpdated = resp;
        this.cd.detectChanges();

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
console.log(this.accountToUpdated)
    if (this.accountToUpdated.type == "CC"){
      console.log("is CC")
      this.banqueAccountService.updateCompteCourant(this.accountToUpdated).subscribe({
        next: () => {
          this.router.navigate(['/nav/accounts', this.accountToUpdated.idClient]);        },
        error: err => {
          console.log(err)
        }
      })

    }else if (this.accountToUpdated.type == "CE") {
      console.log("is CE")
      this.banqueAccountService.updateCompteEpargne(this.accountToUpdated).subscribe({
        next: () => {
          this.router.navigate(['/nav/accounts', this.accountToUpdated.idClient]);        },
        error: err => {
          console.log(err)
        }
      })
    }
}

  returnToAccounts() {

    const clientId = this.accountToUpdated.idClient;

    if (clientId) {
      this.router.navigate(['/nav/accounts', clientId]);
    } else {
      this.router.navigate(['/nav/dashboard']);
    }
  }


}
