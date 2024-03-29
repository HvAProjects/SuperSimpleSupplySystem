import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BoardAdminComponent} from './pages/board-admin/board-admin.component';
import {HomeComponent} from './pages/home/home.component';
import {BoardUserComponent} from './pages/board-user/board-user.component';
import {BoardModeratorComponent} from './pages/board-moderator/board-moderator.component';
import {HeaderComponent} from './components/header/header.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDividerModule} from '@angular/material/divider';
import {HouseholdComponent} from './pages/household/household.component';
import {AddHouseholdDialogComponent} from './components/add-household-dialog/add-household-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatSelectCountryModule} from '@angular-material-extensions/select-country';
import {ScannerComponent} from './pages/scanner/scanner.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {ServiceWorkerModule, SwRegistrationOptions} from '@angular/service-worker';
import {environment} from '../environments/environment';
import {LocationComponent} from './pages/location/location.component';
import {AddLocationDialogComponent} from './dialogs/add-location-dialog/add-location-dialog.component';
import {ProductComponent} from './pages/product/product.component';
import {AddProductDialogComponent} from './dialogs/add-product-dialog/add-product-dialog.component';
import {ScannerDialogComponent} from './dialogs/scanner-dialog/scanner-dialog.component';
import {DeleteProductDialogComponent} from './dialogs/delete-product-dialog/delete-product-dialog.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import {HouseholdUsersDialogComponent} from './dialogs/household-users-dialog/household-users-dialog.component';
import {MatListModule} from '@angular/material/list';
import {EmailAddressPromptComponent} from './dialogs/email-address-prompt/email-address-prompt.component';
import {NotificationComponent} from './components/notification/notification.component';
import {AcceptHouseholdInvitationPromptComponent} from './dialogs/accept-household-invitation-prompt/accept-household-invitation-prompt.component';
import {NotificationListComponent} from './components/notification-list/notification-list.component';
import {AddOrRemoveProductsDialogComponent} from './dialogs/add-or-remove-products-dialog/add-or-remove-products-dialog.component';
import {ScanAnotherProductPromptComponent} from './dialogs/scan-another-product-prompt/scan-another-product-prompt.component';
import {MatSelectModule} from '@angular/material/select';
import {MatSortModule} from '@angular/material/sort';
import {HouseholdSettingsComponent} from './pages/household-settings/household-settings.component';
import {MatGridListModule} from '@angular/material/grid-list';
import {DashboardComponent} from './pages/dashboard/dashboard.component';
import {ItemsAboutToExpireComponent} from './components/items-about-to-expire/items-about-to-expire.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {RecentlyAddedProductsComponent} from './components/recently-added-products/recently-added-products.component';
import {QuickAddRemoveComponent} from './components/quick-add-remove/quick-add-remove.component';
import {YourHouseholdsComponent} from './components/your-households/your-households.component';
import {YourLocationsComponent} from './components/your-locations/your-locations.component';
import { AuthModule } from '@auth0/auth0-angular';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AuthHttpInterceptor } from '@auth0/auth0-angular';
import { SignupButtonComponent } from './components/signup-button/signup-button.component';
import { LogoutButtonComponent } from './components/logout-button/logout-button.component';
import { LoginButtonComponent } from './components/login-button/login-button.component';
import { AuthenticationButtonComponent } from './components/authentication-button/authentication-button.component';

@NgModule({
  declarations: [
    AppComponent,
    BoardAdminComponent,
    HomeComponent,
    BoardUserComponent,
    BoardModeratorComponent,
    HeaderComponent,
    HouseholdComponent,
    AddHouseholdDialogComponent,
    ScannerComponent,
    LocationComponent,
    AddLocationDialogComponent,
    ProductComponent,
    AddProductDialogComponent,
    ScannerDialogComponent,
    DeleteProductDialogComponent,
    HouseholdUsersDialogComponent,
    EmailAddressPromptComponent,
    NotificationComponent,
    AcceptHouseholdInvitationPromptComponent,
    NotificationListComponent,
    AddOrRemoveProductsDialogComponent,
    ScanAnotherProductPromptComponent,
    HouseholdSettingsComponent,
    DashboardComponent,
    ItemsAboutToExpireComponent,
    RecentlyAddedProductsComponent,
    QuickAddRemoveComponent,
    YourHouseholdsComponent,
    YourLocationsComponent,
    UserProfileComponent,
    SignupButtonComponent,
    LogoutButtonComponent,
    LoginButtonComponent,
    AuthenticationButtonComponent
  ],
  imports: [
    BrowserModule,
    AuthModule.forRoot({
      ...environment.auth,
      redirectUri: environment.frontend_url,
      httpInterceptor: {
        allowedList: [`${environment.backend_url}*`],
      },
    }),
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatMenuModule,
    MatTableModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDividerModule,
    MatDialogModule,
    MatSnackBarModule,
    MatSidenavModule,
    MatSelectCountryModule.forRoot('en'),
    // ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production })
    // ServiceWorkerModule.register('ngsw-worker.js'),
    ServiceWorkerModule.register('ngsw-worker.js', {enabled: true}),
    MatListModule,
    MatDialogModule,
    MatSelectModule,
    MatSortModule,
    MatGridListModule,
    MatPaginatorModule
  ],
  providers: [{
      provide: HTTP_INTERCEPTORS,
      useClass: AuthHttpInterceptor,
      multi: true
    },
    {
      provide: SwRegistrationOptions,
      useFactory: () => ({enabled: environment.production}),
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
