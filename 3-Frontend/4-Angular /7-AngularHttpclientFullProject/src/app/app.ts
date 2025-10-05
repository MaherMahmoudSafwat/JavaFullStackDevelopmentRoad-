
// ========================================
// FILE 3: src/app/app.component.ts
// ========================================
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from './services/user.service';

// Interface for User data
interface User {
  id: number;
  name: string;
  email: string;
  username: string;
}

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div>
      <h1>Users List</h1>
      
      <button (click)="loadUsers()">Load Users</button>
      
      <div *ngIf="loading">Loading...</div>
      <div *ngIf="error">Error: {{ error }}</div>
      
      <ul *ngIf="users.length > 0">
        <li *ngFor="let user of users">
          {{ user.name }} - {{ user.email }}
        </li>
      </ul>
    </div>
  `
})
export class AppComponent implements OnInit {
  users: User[] = [];
  loading = false;
  error = '';

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this.loading = true;
    this.error = '';
    
    // Subscribe to the Observable
    this.userService.getUsers().subscribe({
      next: (data) => {
        this.users = data;
        this.loading = false;
        console.log('Users loaded:', data);
      },
      error: (err) => {
        this.error = 'Failed to load users';
        this.loading = false;
        console.error('Error:', err);
      },
      complete: () => {
        console.log('Request completed');
      }
    });
  }

  // Example: Get single user
  getSingleUser() {
    this.userService.getUserById(1).subscribe({
      next: (user) => console.log('Single user:', user),
      error: (err) => console.error('Error:', err)
    });
  }

  // Example: Create new user
  addNewUser() {
    const newUser: User = {
      id: 0,
      name: 'John Doe',
      email: 'john@example.com',
      username: 'johndoe'
    };

    this.userService.createUser(newUser).subscribe({
      next: (user) => console.log('User created:', user),
      error: (err) => console.error('Error:', err)
    });
  }
}