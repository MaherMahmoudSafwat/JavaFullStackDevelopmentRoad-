import { Component } from '@angular/core';

@Component({
  selector: 'app-about',
  standalone: true,
  templateUrl: './about.html'
})
export class AboutComponent {
  title = 'About Us';
  description = 'We are learning Angular Routing!';
}