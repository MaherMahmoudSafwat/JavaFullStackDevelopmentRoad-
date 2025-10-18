import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ 
  providedIn: 'root'
})
export class SurveyService {
  private apiUrl = 'http://localhost:8080/Surveys';

  constructor(private http: HttpClient) {}

  addSurveyQuestion(userId: number, question: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/AddSurvey/${userId}`, {
      Question: question.question,
      Answer: question.answers
    }, { responseType: 'text' });
  }

  // Fixed: Use the correct endpoint from your backend
  getMySurveys(userId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${userId}`);
  }

  // Get all user survey questions (just the question names)
  getAllUserSurveyQuestions(userId: number): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}/ShowAllQuestions/${userId}`);
  }

  // Get question details with answers
  getQuestionDetails(userId: number, questionName: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/QuestionDetails/${userId}/${encodeURIComponent(questionName)}`);
  }

  // Edit survey question and answers
  editSurveyQuestion(userId: number, questionName: string, editDto: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/EditSurveysQuestionAndAnswers/${userId}/${encodeURIComponent(questionName)}`, editDto, { responseType: 'text' });
  }

  // Delete survey
  deleteSurvey(userId: number, questionName: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/DeleteSurvey/${userId}/${encodeURIComponent(questionName)}`, { responseType: 'text' });
  }

  // Get all surveys from all users
  getAllSurveys(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/ShowAllSurveys`);
  }

  // Vote on an answer
  voteOnAnswer(userId: number, questionId: number, answerId: number): Observable<any[]> {
    return this.http.put<any[]>(`${this.apiUrl}/ShowAllSurveys/${userId}`, {
      Question_Id: questionId,
      OldAnswer_Id: null,
      NewAnswer_Id: answerId
    });
  }
}