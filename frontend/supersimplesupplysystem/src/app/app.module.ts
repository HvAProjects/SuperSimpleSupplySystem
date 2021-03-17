import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import { RegisterComponent } from './pages/register/register.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './pages/login/login.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { BoardAdminComponent } from './pages/board-admin/board-admin.component';
import { HomeComponent } from './pages/home/home.component';
import { BoardUserComponent } from './pages/board-user/board-user.component';
import { BoardModeratorComponent } from './pages/board-moderator/board-moderator.component';
import {AuthInterceptor, authInterceptorProviders} from './interceptors/auth.interceptor';
import { HeaderComponent } from './components/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import {MatTableModule} from '@angular/material/table';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDividerModule} from '@angular/material/divider';
import { HouseholdComponent } from './pages/household/household.component';
import { ForgotPasswordComponent } from './pages/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './pages/change-password/change-password.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import { AddHouseholdDialogComponent } from './components/add-household-dialog/add-household-dialog.component';
import {MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatSelectCountryModule} from '@angular-material-extensions/select-country';
import { ScannerComponent } from './pages/scanner/scanner.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    ProfileComponent,
    BoardAdminComponent,
    HomeComponent,
    BoardUserComponent,
    BoardModeratorComponent,
    HeaderComponent,
    HouseholdComponent,
    ForgotPasswordComponent,
    ChangePasswordComponent,
    ActivateAccountComponent,
    AddHouseholdDialogComponent,
    ScannerComponent
  ],
  imports: [
    BrowserModule,
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
    MatSelectCountryModule.forRoot('en'),
    ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production })
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
