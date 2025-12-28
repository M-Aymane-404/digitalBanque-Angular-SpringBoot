import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {BanqueAccountModule} from '../../modules/banqueAccount.module';
import {BanqueAccountService} from '../../services/banque-account.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-new-account',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './new-account.html',
  styleUrl: './new-account.css',
})
export class NewAccount {

  constructor(private banqueAccountService:BanqueAccountService,private route:ActivatedRoute, private router: Router) {}
  newAccount: BanqueAccountModule = {
    type: 'CC',
    solde: 0,
    devise: 'MAD',
    decouvert: 0,
    tauxInteret: 0
  };


  createClient(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
if(id){



    if(this.newAccount.type == "CC"){


    this.banqueAccountService.createCompteCourant(this.newAccount,id).subscribe({
      next:()=>{

        this.router.navigate(['/nav/accounts/'+id]);
        this.newAccount= {
          type: 'CC',
          solde: 0,
          devise: 'MAD',
          decouvert: 0,
          tauxInteret: 0
        };

      },
      error:(err: any) => console.log(err)


    });
    }else if (this.newAccount.type == "CE"){
      this.banqueAccountService.createCompteEpargne(this.newAccount,id).subscribe({
        next:()=>{
          console.log(this.newAccount)

          this.router.navigate(['/nav/accounts/'+id]);
          this.newAccount= {
            type: 'CC',
            solde: 0,
            devise: 'MAD',
            decouvert: 0,
            tauxInteret: 0
          };



        },
        error:(err: any) => console.log(err)


      });

    }}
  }
  returnToAccounts(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id){
      this.router.navigate(['/nav/accounts/'+id]);
    }
  }
}
