import { Component,ChangeDetectorRef } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BanqueAccountService} from '../../services/banque-account.service';
import {ActivatedRoute, Router} from '@angular/router';
import {BanqueAccountModule} from '../../modules/banqueAccount.module';
import {TransactionModule} from '../../modules/transaction.module';
import {TransactionService} from '../../services/transaction.service';
import {NormalizedHttpError} from '../../interceptors/error.interceptor';

@Component({
  selector: 'app-new-transaction',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './new-transaction.html',
  styleUrl: './new-transaction.css',
})
export class NewTransaction {
    loginErrorMessage: string | null = null;



  constructor(private service:TransactionService,private route:ActivatedRoute, private router: Router,private cd :ChangeDetectorRef)  {}

  transtaction:String ="Retrait";
  newTransaction: TransactionModule = {
    type: 'CREDIT'

  };


  createTransaction(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id){
          this.loginErrorMessage = null;




      if(this.transtaction == "virement"){
        this.service.virement(this.newTransaction,id).subscribe({
          next:()=>{

            this.router.navigate(['/nav/transactions/'+id]);
            this.newTransaction= {
              montant: 0,
            type: 'CREDIT' ,
              accIdDestin:"",
              description:""

          };

          },
          error: err => console.log(err)


        });
      }else       if(this.transtaction == "Retrait"){

        this.service.Retrait(this.newTransaction,id).subscribe({
          next:()=>{

            this.router.navigate(['/nav/transactions/'+id]);
            this.newTransaction= {
              montant: 0,
              type: 'CREDIT' ,
              accIdDestin:'',
              description:""

            };



          },
         error: (err: NormalizedHttpError | any) => {
        if (err && typeof err.httpStatus === 'number' && err.httpStatus === 400) {
          this.loginErrorMessage = "account is suspended or not found";
          this.cd.detectChanges();
          return;
        }
        this.loginErrorMessage = (err && typeof err.message === 'string' && err.message.trim().length > 0)
          ? err.message
          : 'Une erreur est survenue. Veuillez réessayer.';
        this.cd.detectChanges();
      }


        });

      }else  if(this.transtaction == "Versement"){
        this.service.Versement(this.newTransaction,id).subscribe({
          next:()=>{
            console.log(this.newTransaction)

            this.router.navigate(['/nav/transactions/'+id]);
            this.newTransaction= {
              montant: 0,
              type: 'CREDIT' ,
              accIdDestin:'',
              description:""

            };


          },
          error: (err: NormalizedHttpError | any) => {
        if (err && typeof err.httpStatus === 'number' && err.httpStatus === 400) {
          this.loginErrorMessage = "account is suspended or not found";
          this.cd.detectChanges();
          return;
        }
        this.loginErrorMessage = (err && typeof err.message === 'string' && err.message.trim().length > 0)
          ? err.message
          : 'Une erreur est survenue. Veuillez réessayer.';
        this.cd.detectChanges();
      }


        });

      }

    }
  }

  returnToTran(){
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if(id){
      this.router.navigate(['/nav/transactions/'+id]);
    }
  }

}

