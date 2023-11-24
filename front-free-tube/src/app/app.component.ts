import { Component, OnInit } from '@angular/core';
import { LoginResponse, OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  constructor(private oidcSecurityService: OidcSecurityService) {}

  ngOnInit(): void {
    this.oidcSecurityService
      .checkAuth()
      .subscribe(({ isAuthenticated })=> {
        console.log('app is authenticated', isAuthenticated);
        this.oidcSecurityService.getAccessToken().subscribe({
          next: data=> {
            console.log("acces-token "+data);
          }
        })
      });
  }
  title = 'front-free-tube';
}
