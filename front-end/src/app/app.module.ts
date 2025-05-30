import {NgModule} from '@angular/core';
import {OAuthModule} from 'angular-oauth2-oidc';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {ComponentModule} from './component/component.module';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptors} from '@angular/common/http';
import {authInterceptor} from './interceptors/auth.interceptor';
import {TransactionsComponent} from './component/transactions/transactions.component';

@NgModule({
  bootstrap: [AppComponent],
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ComponentModule,
    OAuthModule.forRoot({
      resourceServer: {
        allowedUrls: ['http://localhost:8082/*', 'http://localhost:8088/*', 'http://localhost:4200/*'],
        sendAccessToken: true
      }
    }),
    TransactionsComponent
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useValue: authInterceptor,
      multi: true

    },
    provideHttpClient(
      withInterceptors([authInterceptor])
    ),
  ],
})

export class AppModule {
}

