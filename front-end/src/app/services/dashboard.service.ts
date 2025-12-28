import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {DashboardStats} from '../modules/DashboardStats.module';
import {Observable} from 'rxjs';
import {Transactions} from '../transactions/transactions/transactions';
import {Operation} from '../modules/Operation.module';

@Injectable({
  providedIn: 'root',
})
@Injectable({ providedIn: 'root' })
export class DashboardService {

  private apiUrl = 'http://localhost:8080/api/dashboard';

  constructor(private http: HttpClient) {}

  getStats(): Observable<DashboardStats> {
    return this.http.get<DashboardStats>(`${this.apiUrl}/stats`);
  }

  getLastTransactions(): Observable<Operation[]> {
    return this.http.get<Operation[]>(`${this.apiUrl}/transactions`);
  }
}
