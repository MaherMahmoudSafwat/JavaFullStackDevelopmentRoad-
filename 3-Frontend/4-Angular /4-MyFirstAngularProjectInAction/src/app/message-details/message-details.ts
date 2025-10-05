import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-message-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './message-details.html',
  styleUrl: './message-details.css'
})
export class MessageDetailsComponent {
  @Input() users: any = {};
  @Output() deleteUser = new EventEmitter<any>();
  
  onDelete() {
    this.deleteUser.emit(this.users);
  }
}