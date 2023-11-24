import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isAuthenticated: boolean= false;

  constructor(private oidcSecurityService: OidcSecurityService,
              private route: Router) {}

  ngOnInit(): void {
    this.oidcSecurityService.isAuthenticated$.subscribe({
      next: isAuth=> {
        this.isAuthenticated= isAuth.isAuthenticated;
      }
    })
  }

  login() {
    this.oidcSecurityService.authorize();
    this.route.navigateByUrl('/upload-video');

  }
  logout() {
    this.oidcSecurityService.logoffAndRevokeTokens();
    this.isAuthenticated= false;
  }
}
