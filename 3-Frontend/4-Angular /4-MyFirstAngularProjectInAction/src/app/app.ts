import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MessageDetailsComponent } from './message-details/message-details';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    FormsModule,
    CommonModule,
    MessageDetailsComponent
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  name: string = "";
  IsSubmitted: boolean = false;
  Users: Array<any> = [];
  
  onSubmit(): void {
    console.log(this.name);
  }
  
  onSubmit2(): void {
    if (this.name.trim() !== "") {
      this.IsSubmitted = true;
      this.Users.push({
        name: this.name
      });
      this.name = "";
    }
  }
  
  // Delete user function - receives user from child
  deleteUserFromList(user: any) {
    const index = this.Users.indexOf(user);
    if (index > -1) {
      this.Users.splice(index, 1);  // Remove user from array
    }
    console.log('User deleted:', user);
    console.log('Remaining users:', this.Users);
  }
}