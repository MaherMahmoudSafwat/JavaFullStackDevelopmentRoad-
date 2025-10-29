import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './sign-in.html',
  styleUrl: './sign-in.css'
})
export class SignInComponent {
  
  credentials = {
    userEmail: '',
    userPassword: ''
  };

  errorMessage: string = '';
  isLoading: boolean = false;
  showPassword = false;
  
  fieldErrors: { [key: string]: boolean } = {
    userEmail: false,
    userPassword: false
  };

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  onSubmit(): void {
    this.errorMessage = '';
    this.resetFieldErrors();
    this.isLoading = true;

    // Validate fields
    if (!this.credentials.userEmail || !this.credentials.userPassword) {
      this.errorMessage = 'Please fill in all fields';
      
      if (!this.credentials.userEmail) this.fieldErrors['userEmail'] = true;
      if (!this.credentials.userPassword) this.fieldErrors['userPassword'] = true;
      
      this.isLoading = false;
      return;
    }

    // Call backend
    this.authService.signIn(this.credentials.userEmail, this.credentials.userPassword).subscribe({
      next: (user) => {
        console.log('Sign in successful - Full user object:', user);
        this.isLoading = false;
        
        // Debug: Check what properties the user object has
        console.log('User properties:', Object.keys(user));
        console.log('UserId:', user.UserId);
        console.log('UserName:', user.UserName);
        console.log('Email:', user.Email);
        console.log('UserImage:', user.UserImage);
        
        // Store user data in sessionStorage
        sessionStorage.setItem('currentUser', JSON.stringify(user));
        
        // Navigate to dashboard page
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        console.error('Sign in error:', error);
        this.isLoading = false;
        
        // Handle different error types
        if (error.status === 404 || error.error?.includes('Email is invalid')) {
          this.errorMessage = 'Email not found. Please check your email or sign up.';
          this.fieldErrors['userEmail'] = true;
        } else if (error.status === 401 || error.error?.includes('Invalid Password')) {
          this.errorMessage = 'Incorrect password. Please try again.';
          this.fieldErrors['userPassword'] = true;
        } else {
          this.errorMessage = error.error || 'Sign in failed. Please try again.';
        }
      }
    });
  }

  resetFieldErrors(): void {
    Object.keys(this.fieldErrors).forEach(key => {
      this.fieldErrors[key] = false;
    });
  }

  goBack(): void {
    this.router.navigate(['/welcome']);
  }
}