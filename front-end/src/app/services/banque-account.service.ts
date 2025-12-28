import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BanqueAccountModule} from '../modules/banqueAccount.module';

@Injectable({
  providedIn: 'root',
})
export class BanqueAccountService {

  constructor(private http:HttpClient) {
  }


  // compte bancaire
  getAllBanqueAccount() {
    return this.http.get<Array<BanqueAccountModule>>(
      'http://localhost:8080/api/compteBancaires'
    );
  }

  GetAllcompteBancaireByClientId(id:number) {
    return this.http.get<Array<BanqueAccountModule>>(
      'http://localhost:8080/api/compteBancaires/client/'+id
    );
  }


  getBanqueAccountById(id: number) {
    return this.http.get<BanqueAccountModule>(
      'http://localhost:8080/api/compteBancaires/' + id
    );
  }

  getBanqueAccountByaccId(id: string) {
    return this.http.get<BanqueAccountModule>(
      'http://localhost:8080/api/compteBancaires/byAccId/' + id
    );
  }

  deleteBanqueAccount(compte: BanqueAccountModule) {
    return this.http.delete(
      'http://localhost:8080/api/compteBancaires/delete/' + compte.id
    );
  }








  createCompteCourant(compte: BanqueAccountModule, idClient: number) {
    return this.http.post<BanqueAccountModule>(
      'http://localhost:8080/api/comptesCourants/create/' + idClient,
      compte
    );
  }

  updateCompteCourant(compte: BanqueAccountModule) {
    return this.http.put<BanqueAccountModule>(
      'http://localhost:8080/api/comptesCourants/update/' + compte.id,
      compte
    );
  }







  createCompteEpargne(compte: BanqueAccountModule, idClient: number) {
    return this.http.post<BanqueAccountModule>(
      'http://localhost:8080/api/comptesEpargnes/create/' + idClient,
      compte
    );
  }

  updateCompteEpargne(compte: BanqueAccountModule) {
    return this.http.put<BanqueAccountModule>(
      'http://localhost:8080/api/comptesEpargnes/update/' + compte.id,
      compte
    );
  }



}
