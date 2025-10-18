import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SurveyService } from '../../services/survey.service';

interface Answer {
  answerId: number;
  answerName: string;
  answerPercentage: number;
}

@Component({
  selector: 'app-edit-question',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './edit-question.html',
  styleUrl: './edit-question.css'
})
export class EditQuestionComponent implements OnInit {
  
  questionName: string = '';
  originalQuestionName: string = '';
  answers: Answer[] = [];
  originalAnswers: Map<number, string> = new Map();
  
  isLoading = false;
  isSaving = false;
  error: string | null = null;
  successMessage: string | null = null;
  userId: number = 0;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private surveyService: SurveyService
  ) {}

  ngOnInit(): void {
    // Get question name from route params
    this.route.queryParams.subscribe(params => {
      this.originalQuestionName = params['question'];
      if (!this.originalQuestionName) {
        this.router.navigate(['/edit-survey']);
        return;
      }
      this.loadQuestionDetails();
    });
  }

  loadQuestionDetails(): void {
    // Get current user from session storage
    const userStr = sessionStorage.getItem('currentUser');
    
    if (!userStr) {
      this.router.navigate(['/sign-in']);
      return;
    }

    const user = JSON.parse(userStr);
    this.userId = user.UserId;

    console.log('🔍 Loading question details for:', this.originalQuestionName);

    this.isLoading = true;
    this.error = null;

    this.surveyService.getQuestionDetails(this.userId, this.originalQuestionName).subscribe({
      next: (data) => {
        this.questionName = data.questionName;
        this.answers = data.answers;
        
        // Store original answer names for comparison
        this.answers.forEach(answer => {
          this.originalAnswers.set(answer.answerId, answer.answerName);
        });
        
        this.isLoading = false;
        console.log('✅ Question details loaded:', data);
      },
      error: (err) => {
        this.error = 'Failed to load question details. Please try again.';
        this.isLoading = false;
        console.error('❌ Error loading question details:', err);
      }
    });
  }

  saveChanges(): void {
    if (!this.questionName.trim()) {
      this.error = 'Question name cannot be empty!';
      return;
    }

    // Check if any answer is empty
    const hasEmptyAnswer = this.answers.some(answer => !answer.answerName.trim());
    if (hasEmptyAnswer) {
      this.error = 'All answers must have a value!';
      return;
    }

    this.isSaving = true;
    this.error = null;
    this.successMessage = null;

    // Build the HashMap of old -> new answer names
    const oldNewAnswers: { [key: string]: string } = {};
    
    this.answers.forEach(answer => {
      const originalName = this.originalAnswers.get(answer.answerId);
      if (originalName && originalName.trim() !== answer.answerName.trim()) {
        // Only include changed answers
        oldNewAnswers[originalName.trim()] = answer.answerName.trim();
      }
    });

    // Prepare the edit DTO
    const editDto = {
      SurveyQuestion: this.questionName.trim() !== this.originalQuestionName.trim() ? this.questionName.trim() : null,
      OldNewAnswers: oldNewAnswers
    };

    console.log('💾 Saving changes:', editDto);

    this.surveyService.editSurveyQuestion(this.userId, this.originalQuestionName, editDto).subscribe({
      next: (response) => {
        this.successMessage = 'Survey updated successfully! ✅';
        this.isSaving = false;
        console.log('✅ Survey updated:', response);
        
        // Navigate back after 2 seconds
        setTimeout(() => {
          this.router.navigate(['/edit-survey']);
        }, 2000);
      },
      error: (err) => {
        this.error = 'Failed to save changes. Please try again.';
        this.isSaving = false;
        console.error('❌ Error saving changes:', err);
      }
    });
  }

  goBack(): void {
    this.router.navigate(['/edit-survey']);
  }
}