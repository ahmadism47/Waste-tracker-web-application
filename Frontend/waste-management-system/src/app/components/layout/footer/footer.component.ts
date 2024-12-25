import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {

  socialLinks = [
    { url: 'https://www.facebook.com/', icon: 'fab fa-facebook' },
    { url: 'https://www.instagram.com/', icon: 'fab fa-instagram' },
    { url: 'https://www.linkedin.com/', icon: 'fab fa-linkedin' }
  ];
}
