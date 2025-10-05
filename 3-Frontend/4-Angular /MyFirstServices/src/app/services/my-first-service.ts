import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MyFirstService {
  Messages: Array<any> = [];
  
  constructor() {
    this.init();
  }
  
  init(): void {
    this.Insert({
      name: "Samir",
      email: "samir@example.com",
      message: "Welcome to the app!"
    });
  }
  
  Insert(message: {name: string, email: string, message: string}): void {
    this.Messages.push(message);
  }
  
  getMessages(): any[] {
    return this.Messages;
  }
  
  OnDelete(index: number): void {
    this.Messages.splice(index, 1);
  }
}