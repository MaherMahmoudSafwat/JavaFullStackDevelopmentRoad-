import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SurveyService } from '../../services/survey.service';

@Component({
  selector: 'app-show-all-surveys',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './show-all-surveys.html',
  styleUrl: './show-all-surveys.css'
})
export class ShowAllSurveysComponent implements OnInit {
  
  allSurveys: any[] = [];
  isLoading = false;
  error: string | null = null;
  
  // Track which answer the user voted for in each survey
  // Key: surveyId, Value: answerId
  userVotes: Map<number, number> = new Map();
  
  // Track voting state for each survey
  votingInProgress: Map<number, boolean> = new Map();

  constructor(
    private router: Router,
    private surveyService: SurveyService
  ) {}

  ngOnInit(): void {
    this.loadAllSurveys();
  }

  loadAllSurveys(): void {
    console.log('🌍 Loading all surveys from all users...');

    this.isLoading = true;
    this.error = null;

    // Get all surveys from all users
    this.surveyService.getAllSurveys().subscribe({
      next: (surveys) => {
        this.allSurveys = surveys;
        this.isLoading = false;
        console.log('✅ All surveys loaded successfully:', surveys);
        console.log('📊 Total surveys from all users:', surveys.length);
        if (surveys.length > 0) {
          console.log('📋 First survey structure:', surveys[0]);
          console.log('📝 First survey answers:', surveys[0].answer);
        }
      },
      error: (err) => {
        this.error = 'Failed to load surveys. Please try again.';
        this.isLoading = false;
        console.error('❌ Error loading all surveys:', err);
      }
    });
  }

  getAnswerLetter(index: number): string {
    return String.fromCharCode(65 + index); // 65 is ASCII for 'A'
  }

  getUserImageUrl(userImage: any): string {
    if (!userImage || !userImage.imageFile) {
      return '';
    }
    
    // Convert byte array to base64 string
    const base64String = this.arrayBufferToBase64(userImage.imageFile);
    const imageType = userImage.imageType || 'image/jpeg';
    
    return `data:${imageType};base64,${base64String}`;
  }

  arrayBufferToBase64(buffer: any): string {
    // If it's already a string, return it
    if (typeof buffer === 'string') {
      return buffer;
    }
    
    // If it's an array of bytes
    let binary = '';
    const bytes = new Uint8Array(buffer);
    const len = bytes.byteLength;
    for (let i = 0; i < len; i++) {
      binary += String.fromCharCode(bytes[i]);
    }
    return window.btoa(binary);
  }

  getUserInitials(username: string | undefined): string {
    if (!username) {
      return '?';
    }
    
    const names = username.trim().split(' ');
    if (names.length >= 2) {
      return (names[0][0] + names[1][0]).toUpperCase();
    }
    return username.substring(0, 2).toUpperCase();
  }

  goToDashboard(): void {
    this.router.navigate(['/dashboard']);
  }

  // Vote on an answer
  voteOnAnswer(survey: any, answer: any): void {
    const surveyId = survey.surveyId;
    const answerId = answer.answersId;
    
    // Get userId from sessionStorage
    const currentUserJson = sessionStorage.getItem('currentUser');
    if (!currentUserJson) {
      alert('Please log in to vote');
      return;
    }
    
    const currentUser = JSON.parse(currentUserJson);
    const userId = currentUser.UserId;
    
    if (!userId) {
      alert('Please log in to vote');
      return;
    }

    // Prevent multiple simultaneous votes on the same survey
    if (this.votingInProgress.get(surveyId)) {
      console.log('⏳ Vote already in progress for this survey');
      return;
    }
    
    // Check if user is clicking the same answer they already voted for
    const previousVote = this.userVotes.get(surveyId);
    if (previousVote === answerId) {
      console.log('⚠️ You already voted for this answer. No change made.');
      return;
    }

    console.log(`🗳️ Voting on survey ${surveyId}, answer ${answerId}`);
    if (previousVote) {
      console.log(`🔄 Changing vote from answer ${previousVote} to ${answerId}`);
    }
    
    // Mark voting as in progress
    this.votingInProgress.set(surveyId, true);

    // Call the backend to update vote count and percentages
    this.surveyService.voteOnAnswer(parseInt(userId), surveyId, answerId).subscribe({
      next: (updatedAnswers) => {
        console.log('✅ Vote recorded successfully:', updatedAnswers);
        
        // Update only the vote counts and percentages, preserve the original order
        survey.answer.forEach((answer: any) => {
          const updatedAnswer = updatedAnswers.find((a: any) => a.answersId === answer.answersId);
          if (updatedAnswer) {
            answer.answerVoteCount = updatedAnswer.answerVoteCount;
            answer.answerPercentage = updatedAnswer.answerPercentage;
          }
        });
        
        // Track which answer the user voted for
        this.userVotes.set(surveyId, answerId);
        
        // Mark voting as complete
        this.votingInProgress.set(surveyId, false);
        
        console.log('📊 Updated percentages:', survey.answer.map((a: any) => 
          `${a.answerName}: ${a.answerPercentage}%`
        ));
      },
      error: (err) => {
        console.error('❌ Error voting:', err);
        this.votingInProgress.set(surveyId, false);
        alert('Failed to record vote. Please try again.');
      }
    });
  }

  // Check if the user voted for this specific answer
  isAnswerVoted(surveyId: number, answerId: number): boolean {
    return this.userVotes.get(surveyId) === answerId;
  }

  // Check if the user has voted on this survey
  hasVotedOnSurvey(surveyId: number): boolean {
    return this.userVotes.has(surveyId);
  }
}