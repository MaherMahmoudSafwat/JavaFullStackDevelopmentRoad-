import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './contact.html'
})
export class ContactComponent {
  name: string = '';
  email: string = '';
  message: string = '';
  submitted: boolean = false;
  
  onSubmit() {
    if (this.name && this.email && this.message) {
      this.submitted = true;
      console.log('Form submitted:', { name: this.name, email: this.email, message: this.message });
    }
  }
}