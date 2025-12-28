import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TransactionModule} from '../modules/transaction.module';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {



  constructor(private http: HttpClient) {}

  getByAccount(id: number) {
    return this.http.get<TransactionModule[]>(`http://localhost:8080/api/operations/${id}`);
  }


  getById(id:number){
    return this.http.get<TransactionModule>(`http://localhost:8080/api/operation/${id}`);

  }

  Versement(tran:TransactionModule,id:number){
    return this.http.post<TransactionModule>(`http://localhost:8080/api/operations/Versement/${id}`,tran)
  }

  Retrait(tran:TransactionModule,id:number){
    return this.http.post<TransactionModule>(`http://localhost:8080/api/operations/Retrait/${id}`,tran)
  }

  virement(transaction: TransactionModule, idCompte: number) {
    return this.http.post(
      `http://localhost:8080/api/operation/virement/${idCompte}`,
      transaction
    );
  }




}
