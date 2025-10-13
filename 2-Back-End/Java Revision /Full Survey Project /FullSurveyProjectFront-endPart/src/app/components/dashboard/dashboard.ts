import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { User } from '../../models/user.model';
import { Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css'
})
export class DashboardComponent implements OnInit, OnDestroy {
  
  currentUser: User | null = null;
  showUserMenu = false;
  showProfileUpdatedMessage = false;
  private routerSubscription: Subscription = new Subscription();

  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.loadUserData();
    
    // Check for profile update flag
    this.route.queryParams.subscribe(params => {
      if (params['profileUpdated'] === 'true') {
        console.log('🎉 Dashboard: Profile update detected!');
        this.showProfileUpdatedMessage = true;
        setTimeout(() => {
          this.showProfileUpdatedMessage = false;
        }, 3000);
        
        // Clear the parameter
        this.router.navigate([], {
          relativeTo: this.route,
          queryParams: {}
        });
      }
    });
    
    // Listen for navigation events to refresh user data when returning to dashboard
    this.routerSubscription = this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
        if (event.url === '/dashboard') {
          console.log('🔄 Dashboard: Navigation detected, refreshing user data');
          this.loadUserData();
        }
      });
  }

  ngOnDestroy(): void {
    this.routerSubscription.unsubscribe();
  }

  loadUserData(): void {
    console.log('📊 Dashboard: Loading user data from session storage');
    // Get user from sessionStorage
    const userStr = sessionStorage.getItem('currentUser');
    
    if (userStr) {
      this.currentUser = JSON.parse(userStr);
      console.log('✅ Dashboard: User data loaded:', {
        userName: this.currentUser?.UserName,
        email: this.currentUser?.Email,
        hasImage: !!this.currentUser?.UserImage?.imageFile
      });
    } else {
      console.log('❌ Dashboard: No user found, redirecting to sign-in');
      // If no user is logged in, redirect to sign-in
      this.router.navigate(['/sign-in']);
    }
  }

  toggleUserMenu(): void {
    this.showUserMenu = !this.showUserMenu;
  }

  viewUserData(): void {
    this.showUserMenu = false;
    this.router.navigate(['/user-profile']);
  }

  editUserData(): void {
    this.showUserMenu = false;
    this.router.navigate(['/edit-profile']);
  }

  navigateToAllSurveys(): void {
    this.router.navigate(['/all-surveys']);
  }

  navigateToMySurveys(): void {
    this.router.navigate(['/my-surveys']);
  }

  navigateToAddSurvey(): void {
    this.router.navigate(['/add-survey']);
  }

  navigateToEditSurvey(): void {
    this.router.navigate(['/edit-survey']);
  }

  navigateToDeleteSurvey(): void {
    this.router.navigate(['/delete-survey']);
  }

  logout(): void {
    sessionStorage.removeItem('currentUser');
    this.router.navigate(['/welcome']);
  }

  // Close menu when clicking outside
  closeMenuOnClickOutside(event: Event): void {
    const target = event.target as HTMLElement;
    if (!target.closest('.user-menu-container')) {
      this.showUserMenu = false;
    }
  }
}