import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user.model';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-profile.html',
  styleUrl: './edit-profile.css'
})
export class EditProfileComponent implements OnInit {
  
  currentUser: User | null = null;
  fullUserData: any = null;
  isLoading = true;
  isUpdating = false;
  showPassword = false;
  
  // Form data
  editForm = {
    userName: '',
    userPassword: '',
    userImage: null as File | null
  };
  
  // Validation messages
  validationMessages = {
    userName: '',
    userPassword: '',
    userImage: ''
  };
  
  // Image preview
  imagePreview: string | null = null;

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    const userStr = sessionStorage.getItem('currentUser');
    
    if (userStr) {
      this.currentUser = JSON.parse(userStr);
      
      if (this.currentUser?.Email) {
        this.authService.getUserProfile(this.currentUser.Email).subscribe({
          next: (userData) => {
            this.fullUserData = userData;
            this.editForm.userName = userData.userName || '';
            // Don't pre-fill password for security reasons
            this.editForm.userPassword = '';
            console.log('📸 Fresh user data loaded for edit form:', userData);
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

  onImageSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      // Validate file type
      if (!file.type.startsWith('image/')) {
        this.validationMessages.userImage = 'Please select a valid image file';
        return;
      }
      
      // Validate file size (max 5MB)
      if (file.size > 5 * 1024 * 1024) {
        this.validationMessages.userImage = 'Image size should not exceed 5MB';
        return;
      }
      
      this.editForm.userImage = file;
      this.validationMessages.userImage = '';
      
      // Create preview
      const reader = new FileReader();
      reader.onload = (e) => {
        this.imagePreview = e.target?.result as string;
      };
      reader.readAsDataURL(file);
    }
  }

  togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }


  validateForm(): boolean {
    let isValid = true;
    
    // Clear previous validation messages
    this.validationMessages = {
      userName: '',
      userPassword: '',
      userImage: ''
    };
    
    // Validate username only if provided - Backend: ^[A-Za-z0-9]+$ (only letters and numbers)
    if (this.editForm.userName.trim()) {
      const usernameRegex = /^[A-Za-z0-9]+$/;
      if (!usernameRegex.test(this.editForm.userName.trim())) {
        this.validationMessages.userName = 'Username can only contain letters and numbers';
        isValid = false;
      }
    }
    
    // Validate password only if provided - Backend: ^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#$%^&*()_]).{8,}$
    if (this.editForm.userPassword.trim()) {
      const passwordRegex = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#$%^&*()_]).{8,}$/;
      if (!passwordRegex.test(this.editForm.userPassword.trim())) {
        this.validationMessages.userPassword = 'Password must be at least 8 characters with uppercase, lowercase, digit, and special character (#$%^&*()_)';
        isValid = false;
      }
    }
    
    return isValid;
  }

  updateProfile(): void {
    console.log('🔄 Update Profile called!');
    console.log('📝 Form data:', this.editForm);
    
    if (!this.validateForm()) {
      console.log('❌ Validation failed');
      return;
    }
    
    // Only send non-empty fields (matching backend logic)
    const updateData: any = {};
    
    // Only include userName if it's not empty after trim
    if (this.editForm.userName.trim()) {
      updateData.userName = this.editForm.userName.trim();
    }
    
    // Only include userPassword if it's not empty after trim
    if (this.editForm.userPassword.trim()) {
      updateData.userPassword = this.editForm.userPassword.trim();
    }
    
    // Only include userImage if it's not null
    if (this.editForm.userImage) {
      updateData.userImage = this.editForm.userImage;
    }
    
    // Check if at least one field is being updated
    if (Object.keys(updateData).length === 0) {
      // No fields to update, show message and return
      console.log('⚠️ No fields to update');
      alert('Please fill in at least one field to update your profile.');
      return;
    }
    
    console.log('✅ Update data to send:', updateData);
    this.isUpdating = true;
    
    if (this.currentUser?.Email) {
      this.authService.updateUserProfile(this.currentUser.Email, updateData).subscribe({
        next: (response) => {
          console.log('🎉 Profile updated successfully:', response);
          // Fetch latest user profile from backend
          this.authService.getUserProfile(this.currentUser!.Email).subscribe({
            next: (userData) => {
              // Update session storage with new data (username, image, etc)
              const updatedUser = {
                ...this.currentUser,
                UserName: userData.userName || this.currentUser?.UserName,
                UserImage: userData.userImage || this.currentUser?.UserImage
              };
              sessionStorage.setItem('currentUser', JSON.stringify(updatedUser));
              console.log('💾 Session storage updated with latest user data');
              this.isUpdating = false;
              // Navigate back to profile with a flag to refresh data
              this.router.navigate(['/user-profile'], { 
                queryParams: { refresh: 'true' } 
              });
            },
            error: (err) => {
              console.error('Error fetching updated user profile:', err);
              this.isUpdating = false;
              // Still navigate back, but warn user
              this.router.navigate(['/user-profile'], { 
                queryParams: { refresh: 'true' } 
              });
            }
          });
        },
        error: (error) => {
          console.error('Error updating profile:', error);
          this.isUpdating = false;
          // Handle error (you might want to show a toast or error message)
        }
      });
    }
  }

  goBack(): void {
    this.router.navigate(['/user-profile']);
  }

  goToDashboard(): void {
    this.router.navigate(['/dashboard'], { 
      queryParams: { profileUpdated: 'true' } 
    });
  }

  cancelEdit(): void {
    this.router.navigate(['/user-profile']);
  }
}
