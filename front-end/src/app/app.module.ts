import {APP_INITIALIZER, NgModule} from '@angular/core';
import {OAuthModule, OAuthService, OAuthStorage} from 'angular-oauth2-oidc';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {isDefined, LOCAL_STORAGE_TOKEN, localStorageFactory} from './service/local-storage.service';
import {from, Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@NgModule({
  declarations: [
    // MainpageComponent
  ],
  imports: [
    BrowserModule,
    // AppRoutingModule,
    FormsModule,
    OAuthModule.forRoot()
  ],
  providers: [
    // {
    //   provide: APP_INITIALIZER,
    //   useFactory: applicationInitializerFactory,
    //   //deps: [OAuthService],
    //   multi: true
    // },
    {provide: LOCAL_STORAGE_TOKEN, useFactory: localStorageFactory},
    {provide: OAuthStorage, useFactory: localStorageFactory}
  ],
})

export class AppModule {
  ngDoBootstrap() {}
}

export function applicationInitializerFactory(oauthService: OAuthService) {
  // return () =>
  //   new Promise<boolean>((resolve) => {
  //     configure();
  //     oauthService.setupAutomaticSilentRefresh();
  //
  //     // Load Discovery Document and then try to login the user
  //     oauthService
  //       .loadDiscoveryDocument()
  //       .then(() => checkIdentity().subscribe(() => resolve(true)));
  //
  //     function checkIdentity(): Observable<boolean> {
  //       if (isDefined(oauthService.getRefreshToken())) {
  //         return from(oauthService.refreshToken()).pipe(
  //           map(() => oauthService.hasValidAccessToken())
  //         );
  //       }
  //
  //       return from(oauthService.tryLogin());
  //     }
  //
  //     function configure() {
  //       oauthService.configure({
  //         // URL of the SPA to redirect the user to after login
  //         redirectUri: window.location.origin + '/index.html',
  //         // The SPA's id. The SPA is registered with this id at the auth-server
  //         clientId: 'demo-spa',
  //         // set the scope for the permissions the client should request
  //         scope: 'openid',
  //         // url for  /.well-known/openid-configuration endpoint
  //         issuer: 'http://localhost:8888/auth/realms/demo-realm',
  //         disablePKCE: true,
  //         //initialize the code flow
  //         responseType: 'code',
  //         showDebugInformation: true,
  //       });
  //     }
  //   });
}
