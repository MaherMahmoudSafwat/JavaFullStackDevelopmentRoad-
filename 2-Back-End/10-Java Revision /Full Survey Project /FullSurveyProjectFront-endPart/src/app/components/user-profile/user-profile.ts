import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { User } from '../../models/user.model';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-profile.html',
  styleUrl: './user-profile.css'
})
export class UserProfileComponent implements OnInit {
  
  currentUser: User | null = null;
  fullUserData: any = null;
  isLoading = true;
  showPassword = false;
  showSuccessMessage = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    // Check for refresh parameter
    this.route.queryParams.subscribe(params => {
      console.log('🔍 Query params:', params);
      if (params['refresh'] === 'true') {
        console.log('🔄 Refresh flag detected!');
        // Show success message
        this.showSuccessMessage = true;
        setTimeout(() => {
          this.showSuccessMessage = false;
        }, 3000); // Hide after 3 seconds
        
        // Clear the refresh parameter from URL
        this.router.navigate([], {
          relativeTo: this.route,
          queryParams: {}
        });
      }
    });

    this.loadUserProfile();
  }

  loadUserProfile(): void {
    console.log('📊 Loading user profile...');
    const userStr = sessionStorage.getItem('currentUser');
    
    if (userStr) {
      this.currentUser = JSON.parse(userStr);
      console.log('👤 Current user from session:', this.currentUser);
      
      if (this.currentUser?.Email) {
        this.isLoading = true;
        console.log('🌐 Fetching fresh data from backend for:', this.currentUser.Email);
        this.authService.getUserProfile(this.currentUser.Email).subscribe({
          next: (userData) => {
            this.fullUserData = userData;
            console.log('✅ Full user data loaded from backend:', this.fullUserData);
            this.isLoading = false;
          },
          error: (error) => {
            console.error('Error loading profile:', error);
            this.isLoading = false;
          }
        });
      }
    } else {
      this.router.navigate(['/sign-in']);
    }
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  goBack(): void {
    this.router.navigate(['/dashboard']);
  }

  editProfile(): void {
    this.router.navigate(['/edit-profile']);
  }
}