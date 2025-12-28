import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {TransactionModule} from '../../modules/transaction.module';
import {TransactionService} from '../../services/transaction.service';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {DatePipe, DecimalPipe, NgClass} from '@angular/common';
import {BanqueAccountModule} from '../../modules/banqueAccount.module';
import {FormControl, ReactiveFormsModule} from '@angular/forms';
import {BanqueAccountService} from '../../services/banque-account.service';
import {debounceTime, distinctUntilChanged, of, startWith, switchMap} from 'rxjs';

@Component({
  selector: 'app-transactions',
  imports: [
     DecimalPipe,
    DatePipe,
    NgClass,
    RouterLink,
    ReactiveFormsModule
  ],
  templateUrl: './transactions.html',
  styleUrl: './transactions.css',
})
export class Transactions implements OnInit{
  transactions:TransactionModule[]=[];
  transactionSelecter!:TransactionModule;
  accountsOwnerId!:number;
  BannqueAccount!:BanqueAccountModule;
  searchControl = new FormControl('');

  constructor(private service:TransactionService, private serviceAccount:BanqueAccountService,private router:Router ,private route:ActivatedRoute, private cd:ChangeDetectorRef) {
  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.accountsOwnerId = id;
      this.serviceAccount.getBanqueAccountById(this.accountsOwnerId).subscribe(resp => {
        this.BannqueAccount = resp;


        this.cd.detectChanges();

      });
      this.service.getByAccount(id).subscribe(resp => {
        this.transactions = resp;
        if (resp.length > 0) {
          this.transactionSelecter = resp[0];
        }

        this.cd.detectChanges();

      });}



    this.searchControl.valueChanges.pipe(
      startWith(''),
      debounceTime(400),
      distinctUntilChanged(),
      switchMap(value => {
        if (value === null || (typeof value === 'string' && value.trim() === '')) {
          return this.service.getByAccount(this.accountsOwnerId);
        }

        const numericId = Number(value);
        if (!isNaN(numericId)) {
          return this.service.getById(numericId);
        }

        return of([]);

      })
    ).subscribe({
      next: resp => {
        this.transactions = Array.isArray(resp) ? resp : [resp];
        if (this.transactions.length > 0) {
          this.transactionSelecter = this.transactions[0];
        }
        this.cd.detectChanges();
      },
      error: err => console.error(err)
    });
  }



  slectedOp(op: TransactionModule){
    if (op.id) {
      this.service.getById(op.id).subscribe({
        next: resp => {

          console.log(resp);
          this.transactionSelecter = resp;

          this.cd.detectChanges()
        },
        error: err => {
          console.log(err);
        }
      });
    }
  }




  protected readonly Array = Array;
}




