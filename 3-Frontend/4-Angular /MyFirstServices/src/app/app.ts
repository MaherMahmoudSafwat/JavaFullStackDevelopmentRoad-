import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MyFirstService } from './services/my-first-service';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    FormsModule,
    CommonModule
    // ❌ DON'T PUT MyFirstService HERE! Services don't go in imports!
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  name: string = "";
  email: string = "";
  message: string = "";
  
  constructor(private messageService: MyFirstService) {
    console.log('Service injected successfully!');
  }
  
  ngOnInit(): void {
    console.log('Messages from service:', this.messageService.getMessages());
  }
  
  get messages() {
    return this.messageService.getMessages();
  }
  
  addMessage(): void {
    if (this.name.trim() && this.email.trim() && this.message.trim()) {
      this.messageService.Insert({
        name: this.name,
        email: this.email,
        message: this.message
      });
      
      this.name = "";
      this.email = "";
      this.message = "";
      
      console.log('Message added! Total messages:', this.messages.length);
    } else {
      alert('Please fill all fields!');
    }
  }
  
  deleteMessage(index: number): void {
    this.messageService.OnDelete(index);
    console.log('Message deleted! Remaining:', this.messages.length);
  }
}