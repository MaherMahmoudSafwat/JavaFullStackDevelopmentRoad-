import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { SurveyService } from '../../services/survey.service';

interface BackendSurvey {
  surveyId: number;
  surveyQuestionName: string;
  answer: BackendAnswer[];
}

interface BackendAnswer {
  answersId: number;
  answerName: string;
  answerPercentage: number;
}

@Component({
  selector: 'app-my-surveys',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './my-surveys.html',
  styleUrls: ['./my-surveys.css']
})
export class MySurveysComponent implements OnInit {
  mySurveys: BackendSurvey[] = [];
  isLoading = true;
  error = '';

  constructor(
    private surveyService: SurveyService,
    private router: Router
  ) {}

  ngOnInit() {
    this.loadMySurveys();
  }

  loadMySurveys() {
    this.isLoading = true;
    
    // Get current user from session storage
    const userStr = sessionStorage.getItem('currentUser');
    if (!userStr) {
      this.error = 'Please sign in first';
      this.isLoading = false;
      return;
    }

    const user = JSON.parse(userStr);
    console.log('User object from sessionStorage:', user);
    console.log('User object type:', typeof user);
    console.log('User object keys:', Object.keys(user));
    
    // Use uppercase UserId to match your DTO
    const userId = user.UserId;
    
    console.log('Extracted UserId:', userId);
    console.log('UserId type:', typeof userId);

    if (!userId) {
      this.error = 'User ID not found. Available properties: ' + Object.keys(user).join(', ');
      this.isLoading = false;
      return;
    }

    console.log('Making API call to: http://localhost:8080/Surveys/' + userId);

    // Call the service with the correct user ID
    this.surveyService.getMySurveys(userId).subscribe({
      next: (surveys: any) => {
        console.log('Surveys received from backend:', surveys);
        console.log('Number of surveys:', surveys?.length);
        this.mySurveys = surveys;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading surveys - Full error object:', err);
        console.error('Error status:', err.status);
        console.error('Error message:', err.message);
        console.error('Error error:', err.error);
        this.error = 'Failed to load surveys from server. Status: ' + err.status + ', Error: ' + JSON.stringify(err.error);
        this.isLoading = false;
      }
    });
  }

  goToDashboard() {
    this.router.navigate(['/dashboard']);
  }

  getAnswerLetter(index: number): string {
    return String.fromCharCode(65 + index); // A, B, C, D
  }
}