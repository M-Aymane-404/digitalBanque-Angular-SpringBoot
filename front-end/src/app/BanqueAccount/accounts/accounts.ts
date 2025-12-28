import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
 import {FormControl, ReactiveFormsModule} from '@angular/forms';
 import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {debounceTime, distinctUntilChanged, of, startWith, switchMap} from 'rxjs';
import {BanqueAccountService} from '../../services/banque-account.service';
import {BanqueAccountModule} from '../../modules/banqueAccount.module';
import {NgClass} from '@angular/common';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-accounts',
  imports: [
    ReactiveFormsModule,
    NgClass,
    RouterLink
  ],
  templateUrl: './accounts.html',
  styleUrl: './accounts.css',
})
export class Accounts implements OnInit{
  accounts:BanqueAccountModule[]=[];
  accountSelected!:BanqueAccountModule;
  accountsOwnerId!:number;
  searchControl = new FormControl('');

  constructor(private banqueAccountService:BanqueAccountService, private router:Router ,private route:ActivatedRoute, private cd:ChangeDetectorRef,public authService:AuthService) {
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.accountsOwnerId = id;
    this.banqueAccountService.GetAllcompteBancaireByClientId(id).subscribe(resp => {
      this.accounts = resp;
      if (Accounts.length > 0) {
        this.accountSelected = resp[0];
      }

      this.cd.detectChanges();

    });}



    this.searchControl.valueChanges.pipe(
      startWith(''),
      debounceTime(400),
      distinctUntilChanged(),
      switchMap(value => {
        if (!value || value.trim() === '') {
          return this.banqueAccountService.GetAllcompteBancaireByClientId(id);
        }else {
          return this.banqueAccountService.getBanqueAccountByaccId(value);
        }



      })


    ).subscribe({
      next: resp => {
        this.accounts = Array.isArray(resp) ? resp : [resp];
        if (this.accounts.length > 0) {
          this.accountSelected = this.accounts[0];
        }
        this.cd.detectChanges();
      },
      error: err => console.error(err)
    });
  }



  slectedAccount(account:BanqueAccountModule){
    if (account.id) {
      this.banqueAccountService.getBanqueAccountById(account.id).subscribe({
        next: resp => {

console.log(resp);
          this.accountSelected = resp;

          this.cd.detectChanges()
        },
        error: err => {
          console.log(err);
        }
      });
    }
  }
  deleteAccount(account: BanqueAccountModule) {
    if (confirm('vous êtes sûr de supprimer ce Compte ?')) {
      this.accounts = this.accounts.filter(c => c.id !== account.id);

      this.banqueAccountService.deleteBanqueAccount(account).subscribe({
        error: err => console.error(err)
      });
    }
  }


  protected readonly Array = Array;
}




