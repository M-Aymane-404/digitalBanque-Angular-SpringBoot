import { Component, ChangeDetectorRef, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TransactionService } from '../../services/transaction.service';
import { BanqueAccountService } from '../../services/banque-account.service';
import { TransactionModule } from '../../modules/transaction.module';
import { NormalizedHttpError } from '../../interceptors/error.interceptor';

@Component({
  selector: 'app-new-transaction',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './new-transaction.html',
  styleUrl: './new-transaction.css',
})
export class NewTransaction implements OnInit {

  loginErrorMessage: string | null = null;
  isInsufficient: boolean = false;
  accountBalance: number = 0;

  transtaction: string = 'Retrait';

  newTransaction: TransactionModule = {
    type: 'CREDIT',
    montant: 0,
    accIdDestin: '',
    description: ''
  };

  constructor(
    private service: TransactionService,
    private accountService: BanqueAccountService,
    private route: ActivatedRoute,
    private router: Router,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.accountService.getBanqueAccountById(id).subscribe(acc => {
        this.accountBalance = acc.solde ?? 0;
      });

    }
  }

  checkAmount(): void {
    if (
      (this.transtaction === 'Retrait' || this.transtaction === 'virement') &&
      this.newTransaction.montant! > this.accountBalance
    ) {
      this.isInsufficient = true;
      this.loginErrorMessage = 'Insufficient balance';
    } else {
      this.isInsufficient = false;
      this.loginErrorMessage = null;
    }
    this.cd.detectChanges();
  }

  createTransaction(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!id || this.isInsufficient) return;

    if (this.transtaction === 'virement') {
      this.service.virement(this.newTransaction, id).subscribe({
        next: () => this.router.navigate(['/nav/transactions/' + id]),
        error: err => console.log(err)
      });

    } else if (this.transtaction === 'Retrait') {
      this.service.Retrait(this.newTransaction, id).subscribe({
        next: () => this.router.navigate(['/nav/transactions/' + id]),
        error: (err: NormalizedHttpError | any) => {
          this.loginErrorMessage = err.message || 'Error occurred';
        }
      });

    } else if (this.transtaction === 'Versement') {
      this.service.Versement(this.newTransaction, id).subscribe({
        next: () => this.router.navigate(['/nav/transactions/' + id]),
        error: err => console.log(err)
      });
    }
  }

  returnToTran(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.router.navigate(['/nav/transactions/' + id]);
    }
  }
}
