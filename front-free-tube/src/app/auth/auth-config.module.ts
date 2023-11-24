import { NgModule } from '@angular/core';
import { AuthModule } from 'angular-auth-oidc-client';


@NgModule({
    imports: [AuthModule.forRoot({
        config: {
            authority: 'https://dev-jcskqqv7t72fqlv0.us.auth0.com',
            redirectUrl: window.location.origin,
            clientId: 'zPcOi6kFmiIrQEd9ruPCqbmw1WmihmVe',
            scope: 'openid profile offline_access email',
            responseType: 'code',
            silentRenew: true,
            useRefreshToken: true,
            secureRoutes: ['http://localhost:7100'],
            customParamsAuthRequest: {
                audience: 'http://localhost:7100'
            }

        }
      })],
    exports: [AuthModule],
})
export class AuthConfigModule {}
