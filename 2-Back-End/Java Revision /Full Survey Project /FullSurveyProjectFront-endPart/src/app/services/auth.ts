import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User, UserSignUp } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/Users';

  constructor(private http: HttpClient) { }

  signUp(user: UserSignUp, image: File): Observable<string> {
    const formData = new FormData();
    
    const userBlob = new Blob([JSON.stringify(user)], { 
      type: 'application/json' 
    });
    
    formData.append('user', userBlob);
    formData.append('image', image);

    return this.http.post<string>(`${this.baseUrl}/AddNewUser`, formData, {
      responseType: 'text' as 'json'
    });
  }

  signIn(email: string, password: string): Observable<User> {
    console.log('Signing in with:', { email, password: '***' });
    
    return this.http.post<User>(`${this.baseUrl}/SignInToMyAccount`, {
      userEmail: email,
      userPassword: password
    });
  }

  getUserProfile(email: string): Observable<any> {
    console.log('Fetching profile for:', email);
    return this.http.get<any>(`${this.baseUrl}/ViewAllUserAccountProfile/${email}`);
  }

  updateUserProfile(email: string, updateData: any): Observable<string> {
    console.log('Updating profile for:', email, updateData);
    
    const formData = new FormData();
    
    const userUpdateData = {
      UserName: updateData.userName || null,
      UserPassword: updateData.userPassword || null,
      UserImages: null
    };
    
    const userBlob = new Blob([JSON.stringify(userUpdateData)], { 
      type: 'application/json' 
    });
    
    formData.append('user', userBlob);
    
    if (updateData.userImage) {
      formData.append('image', updateData.userImage);
    }

    return this.http.put<string>(`${this.baseUrl}/EditAndUpdateUserAccount/${email}`, formData, {
      responseType: 'text' as 'json'
    });
  }
}