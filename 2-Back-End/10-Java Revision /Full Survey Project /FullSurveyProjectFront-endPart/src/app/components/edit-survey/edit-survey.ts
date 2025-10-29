import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SurveyService } from '../../services/survey.service';

@Component({
  selector: 'app-edit-survey',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './edit-survey.html',
  styleUrl: './edit-survey.css'
})
export class EditSurveyComponent implements OnInit {
  
  surveys: any[] = [];
  isLoading = false;
  error: string | null = null;

  constructor(
    private router: Router,
    private surveyService: SurveyService
  ) {}

  ngOnInit(): void {
    this.loadSurveyQuestions();
  }

  loadSurveyQuestions(): void {
    // Get current user from session storage
    const userStr = sessionStorage.getItem('currentUser');
    
    if (!userStr) {
      this.router.navigate(['/sign-in']);
      return;
    }

    const user = JSON.parse(userStr);
    const userId = user.UserId;

    console.log('🔍 Loading surveys for user ID:', userId);

    this.isLoading = true;
    this.error = null;

    // Use getMySurveys to get full survey data with answers
    this.surveyService.getMySurveys(userId).subscribe({
      next: (surveys) => {
        this.surveys = surveys;
        this.isLoading = false;
        console.log('✅ Surveys loaded successfully:', surveys);
        console.log('📊 Total surveys:', surveys.length);
      },
      error: (err) => {
        this.error = 'Failed to load your surveys. Please try again.';
        this.isLoading = false;
        console.error('❌ Error loading surveys:', err);
        console.error('❌ Error status:', err.status);
        console.error('❌ Error message:', err.message);
        console.error('❌ Full error:', err);
      }
    });
  }

  getAnswerLetter(index: number): string {
    return String.fromCharCode(65 + index); // 65 is ASCII for 'A'
  }

  goToDashboard(): void {
    this.router.navigate(['/dashboard']);
  }

  editQuestion(survey: any): void {
    console.log('🔧 Editing question:', survey.surveyQuestionName);
    this.router.navigate(['/edit-question'], { 
      queryParams: { question: survey.surveyQuestionName } 
    });
  }
}