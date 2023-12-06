import { Injectable } from '@angular/core';
import { UserDto } from '../model/user-dto';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  

  constructor(private http: HttpClient) { }

  public register(): Observable<UserDto> {
    return this.http.get<UserDto>(environment.backEndUserHost+"/register");
  }

  public getUser(userId: string): Observable<UserDto> {
    return this.http.get<UserDto>(environment.backEndUserHost+"/get/"+userId);
  }

  public subscribeToUser(userId: string): Observable<UserDto> {
    return this.http.get<UserDto>(environment.backEndUserHost+"/subscribe/"+userId);
  }

  public unSubscribeToUser(userId: string): Observable<UserDto> {
    return this.http.get<UserDto>(environment.backEndUserHost+"/unSubscribe/"+userId);
  }
}
