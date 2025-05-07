import {Component, OnInit} from '@angular/core';
import {OAuthService} from 'angular-oauth2-oidc';
import {authCodeFlowConfig} from './service/auth-config.service';

@Component({
  standalone: false,
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {

  userName: string = '';

  constructor(protected oauthService: OAuthService) {
  }

  ngOnInit(): void {
    this.oauthService.configure(authCodeFlowConfig);
    this.oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
      this.userName = this.oauthService.getIdentityClaims()['name'];
    });
  }

  signIn() {
    this.oauthService.initCodeFlow();
  }

  signOut() {
    this.oauthService.logOut();
  }
}
