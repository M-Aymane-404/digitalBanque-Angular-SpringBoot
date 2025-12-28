import {
  AfterViewInit, ChangeDetectorRef,
  Component,
  ElementRef,
  ViewChild
} from '@angular/core';
import {DatePipe, DecimalPipe, NgClass} from '@angular/common';
import { DashboardService } from '../services/dashboard.service';
import { DashboardStats } from '../modules/DashboardStats.module';
 import {
  Chart,
  LineController,
  LineElement,
  PointElement,
  LinearScale,
  CategoryScale,
  BarController,
  BarElement,
  ArcElement,
  DoughnutController,
  Tooltip,
  Legend, Filler
} from 'chart.js';
 import {Operation} from '../modules/Operation.module';
import {RouterLink} from '@angular/router';

Chart.register(
  LineController,
  LineElement,
  PointElement,
  LinearScale,
  CategoryScale,
  BarController,
  BarElement,
  DoughnutController,
  ArcElement,
  Tooltip,
  Legend,
  Filler
);

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NgClass, DecimalPipe, DatePipe, RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements AfterViewInit {
  stats!: DashboardStats;
  lastTransactions: Operation[] = [];

  @ViewChild('activityChart') activityChart!: ElementRef;

  @ViewChild('distributionChart') distributionChart!: ElementRef;

  constructor(private dashboardService: DashboardService, private cd:ChangeDetectorRef) {}

  ngAfterViewInit(): void {
    this.loadDashboard();
  }

  loadDashboard() {
    this.dashboardService.getStats().subscribe(stats => {
      this.stats = stats;
      this.createCharts();
      this.cd.detectChanges();
    });

    this.dashboardService.getLastTransactions().subscribe(data => {
      this.lastTransactions = data;
      this.cd.detectChanges();

    });
  }

  getMonthlyTransactionStats() {
    const now = new Date();
    const m1 = now.getMonth() - 2;
    const m2 = now.getMonth() - 1;
    const m3 = now.getMonth();

    const counts = [0, 0, 0];

    this.lastTransactions.forEach(t => {
      if (!t.dateOp) return;

      const txMonth = new Date(t.dateOp).getMonth();

      if (txMonth === m1) counts[0]++;
      else if (txMonth === m2) counts[1]++;
      else if (txMonth === m3) counts[2]++;
    });

    return counts;
  }


  createCharts() {

    // ===== Activity =====
    const activityData = this.getMonthlyTransactionStats();

    new Chart(this.activityChart.nativeElement, {
      type: 'line',
      data: {
        labels: ['M1', 'M2', 'M3'],
        datasets: [{
          label: 'Transactions',
          data: activityData,
          borderColor: '#22c55e',
          backgroundColor: 'rgba(34,197,94,0.15)',
          fill: true,
          tension: 0.45
        }]
      }
    });




    // ===== Distribution =====
    new Chart(this.distributionChart.nativeElement, {
      type: 'doughnut',
      data: {
        labels: ['Savings Account', 'Checking Account'],
        datasets: [{
          data: [
            this.stats.epargneAccounts,
            this.stats.courantAccounts
          ],
          backgroundColor: ['#8b5cf6', '#3b82f6'],
          borderWidth: 0
        }]
      },
      options: {
        cutout: '70%',
        plugins: {
          legend: {
            position: 'bottom',
            labels: { usePointStyle: true }
          }
        }
      }
    });
  }
}
