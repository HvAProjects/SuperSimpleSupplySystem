import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from './pages/register/register.component';
import {LoginComponent} from './pages/login/login.component';
import {BoardAdminComponent} from './pages/board-admin/board-admin.component';
import {ProfileComponent} from './pages/profile/profile.component';
import {HomeComponent} from './pages/home/home.component';
import {BoardUserComponent} from './pages/board-user/board-user.component';
import {BoardModeratorComponent} from './pages/board-moderator/board-moderator.component';
import {HouseholdComponent} from './pages/household/household.component';
import {ForgotPasswordComponent} from './pages/forgot-password/forgot-password.component';
import {ChangePasswordComponent} from './pages/change-password/change-password.component';
import {ActivateAccountComponent} from './pages/activate-account/activate-account.component';
import {ScannerComponent} from './pages/scanner/scanner.component';
import {LocationComponent} from './pages/location/location.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'change-password', component: ChangePasswordComponent },
  { path: 'activate-account', component: ActivateAccountComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'household', component: HouseholdComponent},
  { path: 'household/:id', component: LocationComponent},
  { path: 'scanner', component: ScannerComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  // imports: [RouterModule.forRoot(routes)],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
