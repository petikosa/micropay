import { AuthConfig } from 'angular-oauth2-oidc';

export const authCodeFlowConfig: AuthConfig = {
  issuer: 'http://localhost:8088/realms/micro',
  redirectUri: 'http://localhost:4200/',
  clientId: 'micro-client',
  responseType: 'code',
  showDebugInformation: true,
  requireHttps: false
};
