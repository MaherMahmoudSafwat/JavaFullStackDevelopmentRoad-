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
}