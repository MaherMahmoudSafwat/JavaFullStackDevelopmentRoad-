import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Survey, SurveyQuestion } from '../../models/survey.model';
import { SurveyService } from '../../services/survey.service';

@Component({
  selector: 'app-survey',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './survey.html',
  styleUrls: ['./survey.css']
})

export class SurveyComponent {
  question = '';
  answers = ['', '', '', ''];
  // Controls which answer fields are visible (0-based index, -1 means none)
  currentAnswerIndex = -1;
  showAnswers = false;
  questionsList: SurveyQuestion[] = [];
  mySurveys: Survey[] = [];
  error = '';
  success = '';

  // Called when typing in the question field
  onQuestionInput() {
    if (!this.question.trim()) {
      this.showAnswers = false;
      this.resetAnswers();
    }
  }

  // Only allow editing the current answer field
  canEditAnswer(i: number): boolean {
    return i <= this.currentAnswerIndex;
  }

  // Called when typing in an answer field
  onAnswerInput(i: number) {
    // If the current answer is filled, allow the next one to be revealed
    if (i === this.currentAnswerIndex && this.answers[i].trim().length > 0 && this.currentAnswerIndex < 3) {
      // Do nothing here; plus button will appear
    }
  }

  // Show the plus button only if the current answer is filled and not the last
  canShowPlus(i: number): boolean {
    return (
      i === this.currentAnswerIndex &&
      this.answers[i].trim().length > 0 &&
      this.currentAnswerIndex < 3
    );
  }

  // Reveal the next answer field
  revealNextAnswer(i: number) {
    if (i === this.currentAnswerIndex && this.currentAnswerIndex < 3) {
      this.currentAnswerIndex++;
    }
  }

  // Only allow submit if all fields are filled
  canSubmit(): boolean {
    return (
      this.question.trim().length > 0 &&
      this.answers.every(a => a.trim().length > 0)
    );
  }

  // Simulate answer percentages for demo (replace with real data if available)
  getAnswerPercent(q: SurveyQuestion, aIdx: number): number {
    // For demo, randomize or distribute equally
    const total = q.answers.length * 10;
    if (!total) return 0;
    // You can replace this with real answer stats from backend
    return Math.round(100 / q.answers.length);
  }

  constructor(private surveyService: SurveyService) {}

  goToDashboard() {
    // Use Angular router to go back to dashboard
    window.location.href = '/dashboard';
  }

  canAddQuestion(): boolean {
    return (
      this.question.trim().length > 0 &&
      this.answers.every(a => a.trim().length > 0)
    );
  }


  // Not used anymore; replaced by revealNextAnswer


  resetAnswers() {
    this.answers = ['', '', '', ''];
    this.currentAnswerIndex = -1;
    this.showAnswers = false;
  }


  addQuestion() {
    if (this.canAddQuestion()) {
      this.questionsList.push({
        question: this.question,
        answers: [...this.answers]
      });
      this.question = '';
      this.showAnswers = false;
      this.resetAnswers();
    }
  }

  canAddSurvey(): boolean {
    return this.questionsList.length > 0;
  }


  addSurvey() {
    if (!this.canSubmit()) return;
    const userStr = sessionStorage.getItem('currentUser');
    if (!userStr) {
      this.error = 'User not found. Please sign in again.';
      return;
    }
    const user = JSON.parse(userStr);
    const userId = user.UserId || user.id || user.userId;
    if (!userId) {
      this.error = 'User ID not found.';
      return;
    }
    this.surveyService.addSurveyQuestion(userId, {
      question: this.question,
      answers: [...this.answers]
    }).subscribe({
      next: () => {
        this.success = 'Survey has been sent successfully!';
        this.error = '';
        this.question = '';
        this.resetAnswers();
      },
      error: () => {
        this.error = 'Failed to send survey.';
        this.success = '';
      }
    });
  }

  showMySurveys() {
    const userStr = sessionStorage.getItem('currentUser');
    if (!userStr) {
      this.error = 'User not found. Please sign in again.';
      return;
    }
    const user = JSON.parse(userStr);
    const userId = user.UserId || user.id || user.userId;
    if (!userId) {
      this.error = 'User ID not found.';
      return;
    }
    this.surveyService.getMySurveys(userId).subscribe({
      next: surveys => (this.mySurveys = surveys),
      error: () => (this.error = 'Failed to fetch surveys')
    });
  }
}
