import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { CommonModule } from '@angular/common';
import { User } from '../../models/user.model';
import { Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class HomeComponent implements OnInit, OnDestroy {
  
  currentUser: User | null = null;
  private routerSubscription: Subscription = new Subscription();

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.loadUserData();
    
    // Listen for navigation events to refresh user data when returning to home
    this.routerSubscription = this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        if (event.url === '/home') {
          this.loadUserData();
        }
      });
  }

  ngOnDestroy(): void {
    this.routerSubscription.unsubscribe();
  }

  loadUserData(): void {
    // Get user from sessionStorage
    const userStr = sessionStorage.getItem('currentUser');
    
    if (userStr) {
      this.currentUser = JSON.parse(userStr);
    } else {
      // If no user is logged in, redirect to sign-in
      this.router.navigate(['/sign-in']);
    }
  }

  logout(): void {
    sessionStorage.removeItem('currentUser');
    this.router.navigate(['/welcome']);
  }
}