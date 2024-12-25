import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { inject } from '@angular/core';
import { map, take } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  
  const authService = inject(AuthService);
  const router = inject(Router); // instead of constructor

  return authService.isAuthenticated().pipe(
    take(1),
    map(isAuthenticated => {

      console.log('Guard check - isAuh=thenticated:' , isAuthenticated);
      if(isAuthenticated){
        return true;
      }

      // to rerdirect the login if not authenticated
      console.log('Not authenticated, redirecting to login');
      return router.createUrlTree(['/login']);
    })
  )
};
