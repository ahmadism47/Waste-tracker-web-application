import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from '../services/auth/auth.service';
import { Router } from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
   
    return next.handle(request).pipe( // passes the request forward
   
        catchError((error: HttpErrorResponse) => {   // catches errors if occured
        let errorMessage = 'An unknown error occurred!';

        if (error.error instanceof ErrorEvent) {  // if client side errors: the event did not reach the server
          errorMessage = error.error.message;
        } 
        else {                        // server side errors: occurs after receiving the request
          switch (error.status) {
            case 401:
              this.authService.logout();
              void this.router.navigateByUrl('/login'); 
              errorMessage = 'Please log in to continue';
              break;
            case 403:
              errorMessage = 'You do not have permission to perform this action';
              break;
            case 404:
              errorMessage = 'Resource not found';
              break;
            case 500:
              errorMessage = 'Server error occurred. Please try again later';
              break;
            default:
              errorMessage = `Error: ${error.message}`;
          }
        }

        console.error(errorMessage);
        return throwError(() => new Error(errorMessage));
      })
    );
  }
}