import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";


export interface ErrorState {
    message: string;
    isError: boolean;
  }

  @Injectable({
    providedIn: 'root'
  })
  export class ErrorHandlinngService {

    private errorSubject = new BehaviorSubject<ErrorState>({
        message: '',
        isError: false
    });
    
    error$ = this.errorSubject.asObservable(); // $ means an observable

    showError(message: string): void {
        this.errorSubject.next({
            message,
            isError: true
        });

        setTimeout(() => {
            this.clearError();
        }, 5000);
    }
     
    clearError(): void{
        this.errorSubject.next({
            message: '',
            isError: false
        });
    }

    getError(): Observable<ErrorState> {
        return this.error$;
    }

  }