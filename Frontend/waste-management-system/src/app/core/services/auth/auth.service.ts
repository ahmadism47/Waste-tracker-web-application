import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { environment } from '@env/environment';
import { BehaviorSubject, map, Observable, tap } from 'rxjs';
import { AuthState, LoginRequest, LoginResponse, User } from '../../../interfaces/auth/auth';
import { HttpClient } from '@angular/common/http';
import { isPlatformBrowser } from '@angular/common';

@Injectable({ //makes this class available for dependencies
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = environment.apiUrl;

  private readonly authState = new BehaviorSubject<AuthState>({ //readonly: the reference oh behaviorsubject cannot be changed but the value of it can b changed using next(), ex: we cannot do: this.auth =...
    isAuthenticated : false,
    user: null,
    token: null
  });



  constructor(private http: HttpClient, @Inject(PLATFORM_ID) private platformId: Object
) {
  if (isPlatformBrowser(this.platformId)){
    this.initializeAuthState();
    }
   }

   private initializeAuthState(): void{
    if (isPlatformBrowser(this.platformId)){
    const token = localStorage.getItem('token'); //return a string format
    const user = localStorage.getItem('user');

    if(token && user){
      try{ // try mtethod because token and user might be null
          
        this.authState.next({
          isAuthenticated : true,
          user: JSON.parse(user), // convert this string back into a typeScript Object
          token: token
        });
      }catch(error){
        console.error('Error parsong user data', error);
      }
    }
   }
  }

   login(credentials: LoginRequest): Observable<LoginResponse>{
    const loginUrl = `${this.apiUrl}/auth/login`;

    return this.http.post<LoginResponse>(loginUrl, credentials)      //send an http POST request using httpClient
      .pipe(  // apply operation to the returned response from the observable                         // ${..} for evaluing the value inside then turns it into a string

        tap((response) => this.handleAuthentication(response)) //executing the handleAuthentication function on the response without modifying the ibserverr
      );
   }

  private handleAuthentication(response: LoginResponse): void{
    localStorage.setItem('token', response.token);
    localStorage.setItem('user', JSON.stringify(response.user));

    this.authState.next({
      isAuthenticated: true,
      user: response.user,
      token: response.token
    });
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    
    this.authState.next({
      isAuthenticated: false,
      user: null,
      token: null
    });
  }

  //to check whether the user is authenticated or not
  isAuthenticated(): Observable<boolean> {
    return this.authState.pipe(
      map(state => {
        console.log('Current auth state:', state); // Debug log
        return state.isAuthenticated;
      }) // from bottom to top, we used map() to access isAuthenticated because authState is an observable object
                                        // state refers to each objec emotted by the observable authState
    );
  }

  getCurrentUser(): Observable<User | null> {
    return this.authState.pipe(
      map(state => state.user)
    );
  }
  
  getToken(): string | null {
    return this.authState.value.token;
  }

}

