import {NgModule} from '@angular/core';
import {OAuthModule, OAuthStorage, provideOAuthClient} from 'angular-oauth2-oidc';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {HttpClientModule, provideHttpClient, withInterceptorsFromDi} from '@angular/common/http';
import {AppComponent} from './app.component';
import {ComponentModule} from './component/component.module';

@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ComponentModule,
    HttpClientModule,
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: ['http://localhost:8082/*', 'http://localhost:8088'],
        sendAccessToken: true
      }
    })
  ],
  providers: [
    // provideHttpClient(),
    // provideOAuthClient()
  ],
})

export class AppModule {
}

