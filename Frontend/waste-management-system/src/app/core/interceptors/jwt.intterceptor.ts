import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";
import { AuthService } from "../services/auth/auth.service";
import { Injectable } from "@angular/core";


@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    
    constructor(private authService: AuthService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> { //it intercepts every HTTP request before it is sent to the server
        const token = this.authService.getToken();
        if(token) {
            const clonedReq = req.clone({ //clone because the request are immutable: cannot change
                setHeaders: {
                    Authorization: 'bearer ${token}' // puttint the tokens in authorization's header
                }
            });
            return next.handle(clonedReq);//returning the copy; using the next: forwarding the intercept for the next step of http piplines
        }
        return next.handle(req); //if no token existed than returning the response
    }

}