import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OidcSecurityService } from 'angular-auth-oidc-client';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isAuthenticated: boolean= false;

  constructor(private oidcSecurityService: OidcSecurityService,
              private userService: UserService,
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
    this.route.navigateByUrl('/history');
  }
  logout() {
    this.oidcSecurityService.logoffAndRevokeTokens();  
    this.isAuthenticated= false;
  }
  
  uploadVideo() {
    this.route.navigateByUrl("/upload-video"); 
    console.log("navigate successful");
  }

  logo() {
    this.route.navigateByUrl("/featured");
  }
}
