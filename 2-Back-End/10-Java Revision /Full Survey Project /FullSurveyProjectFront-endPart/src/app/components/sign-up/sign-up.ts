import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { UserSignUp } from '../../models/user.model';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './sign-up.html',
  styleUrl: './sign-up.css'
})
export class SignUpComponent {
  
  user: UserSignUp = {
    userName: '',
    userEmail: '',
    userPassword: ''
  };

  selectedFile: File | null = null;
  errorMessages: string[] = [];
  successMessage: string = '';
  showPassword = false;
  
  fieldErrors: { [key: string]: boolean } = {
    userName: false,
    userEmail: false,
    userPassword: false,
    image: false
  };

  constructor(
    private authService: AuthService,
    private router: Router
  ) { }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      this.fieldErrors['image'] = false;
    }
  }

  onSubmit(): void {
    this.errorMessages = [];
    this.successMessage = '';
    this.resetFieldErrors();

    // Validate all fields
    if (!this.user.userName || !this.user.userEmail || 
        !this.user.userPassword || !this.selectedFile) {
      this.errorMessages.push('Please fill in all fields and select an image');
      
      if (!this.user.userName) this.fieldErrors['userName'] = true;
      if (!this.user.userEmail) this.fieldErrors['userEmail'] = true;
      if (!this.user.userPassword) this.fieldErrors['userPassword'] = true;
      if (!this.selectedFile) this.fieldErrors['image'] = true;
      
      return;
    }

    // Call backend
    this.authService.signUp(this.user, this.selectedFile).subscribe({
      next: (response) => {
        this.successMessage = response;
        setTimeout(() => {
          this.router.navigate(['/sign-in']);
        }, 2000);
      },
      error: (error) => {
        console.error('Sign up error:', error);
        console.error('Full error object:', JSON.stringify(error, null, 2));
        
        // Backend returns array of error strings
        if (error.error) {
          let errorMessages = error.error;
          if (typeof errorMessages === 'string') {
            try {
              errorMessages = JSON.parse(errorMessages);
            } catch (e) {
              errorMessages = [errorMessages];
            }
          }
          
          if (Array.isArray(errorMessages)) {
            this.errorMessages = errorMessages;
            
            // Mark fields with errors as red
            errorMessages.forEach((err: string) => {
              if (err.toLowerCase().includes('username')) 
                this.fieldErrors['userName'] = true;
              if (err.toLowerCase().includes('email')) 
                this.fieldErrors['userEmail'] = true;
              if (err.toLowerCase().includes('password')) 
                this.fieldErrors['userPassword'] = true;
            });
          } else {
            this.errorMessages = [errorMessages];
          }
        } else {
          this.errorMessages = ['Sign up failed. Please try again.'];
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