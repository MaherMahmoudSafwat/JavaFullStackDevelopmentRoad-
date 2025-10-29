import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SurveyService } from '../../services/survey.service';

@Component({
  selector: 'app-delete-survey',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './delete-survey.html',
  styleUrl: './delete-survey.css'
})
export class DeleteSurveyComponent implements OnInit {
  
  surveys: any[] = [];
  isLoading = false;
  error: string | null = null;
  userId: number = 0;
  
  // Confirmation dialog state
  showConfirmDialog = false;
  surveyToDelete: any = null;

  constructor(
    private router: Router,
    private surveyService: SurveyService
  ) {}

  ngOnInit(): void {
    this.loadSurveys();
  }

  loadSurveys(): void {
    // Get current user from session storage
    const userStr = sessionStorage.getItem('currentUser');
    
    if (!userStr) {
      this.router.navigate(['/sign-in']);
      return;
    }

    const user = JSON.parse(userStr);
    this.userId = user.UserId;

    console.log('🔍 Loading surveys for deletion for user ID:', this.userId);

    this.isLoading = true;
    this.error = null;

    // Use getMySurveys to get full survey data with answers
    this.surveyService.getMySurveys(this.userId).subscribe({
      next: (surveys) => {
        this.surveys = surveys;
        this.isLoading = false;
        console.log('✅ Surveys loaded successfully:', surveys);
        console.log('📊 Total surveys:', surveys.length);
        if (surveys.length > 0) {
          console.log('📋 First survey structure:', surveys[0]);
          console.log('📝 First survey answers:', surveys[0].answer);
        }
      },
      error: (err) => {
        this.error = 'Failed to load your surveys. Please try again.';
        this.isLoading = false;
        console.error('❌ Error loading surveys:', err);
      }
    });
  }

  getAnswerLetter(index: number): string {
    return String.fromCharCode(65 + index); // 65 is ASCII for 'A'
  }

  goToDashboard(): void {
    this.router.navigate(['/dashboard']);
  }

  // Open confirmation dialog
  openDeleteConfirmation(survey: any): void {
    this.surveyToDelete = survey;
    this.showConfirmDialog = true;
  }

  // Close confirmation dialog
  closeDeleteConfirmation(): void {
    this.showConfirmDialog = false;
    this.surveyToDelete = null;
  }

  // Confirm and delete survey
  confirmDelete(): void {
    if (!this.surveyToDelete) return;

    const questionName = this.surveyToDelete.surveyQuestionName;
    
    console.log('🗑️ Deleting survey:', questionName);

    this.surveyService.deleteSurvey(this.userId, questionName).subscribe({
      next: (response) => {
        console.log('✅ Survey deleted successfully:', response);
        
        // Remove the survey from the list
        this.surveys = this.surveys.filter(s => s.surveyQuestionName !== questionName);
        
        // Close the dialog
        this.closeDeleteConfirmation();
      },
      error: (err) => {
        console.error('❌ Error deleting survey:', err);
        this.error = 'Failed to delete survey. Please try again.';
        this.closeDeleteConfirmation();
      }
    });
  }
}