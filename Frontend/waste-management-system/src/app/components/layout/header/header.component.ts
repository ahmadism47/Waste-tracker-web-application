import { CommonModule } from '@angular/common';
import { Component, ViewEncapsulation } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../../core/services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})

export class HeaderComponent {

  isAuthenticated$: Observable<boolean>; // we use observable because this variable is gonna change over time, so in ibservable case in updates auto

  constructor( private authService: AuthService, private router: Router)
  {
    this.isAuthenticated$ = this.authService.isAuthenticated();
  }

    currentLang = 'fr';

    changeLanguage(lang: string){
      this.currentLang = lang;
    }

    logout(): void {
      this.authService.logout();
      this.router.navigate(['/login']);
    }
}
