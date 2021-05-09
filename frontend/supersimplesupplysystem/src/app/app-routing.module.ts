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
import {ProductComponent} from './pages/product/product.component';
import {HouseholdSettingsComponent} from './pages/household-settings/household-settings.component';
import {DashboardComponent} from './pages/dashboard/dashboard.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'forgot-password', component: ForgotPasswordComponent},
  {path: 'change-password', component: ChangePasswordComponent},
  {path: 'activate-account', component: ActivateAccountComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'user', component: BoardUserComponent},
  {path: 'mod', component: BoardModeratorComponent},
  {path: 'admin', component: BoardAdminComponent},
  {path: 'household', component: HouseholdComponent},
  {path: 'household/:householdId', component: ProductComponent},
  {path: 'scanner', component: ScannerComponent},
  {path: 'household-settings/:householdId', component: HouseholdSettingsComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'}
];

@NgModule({
  // imports: [RouterModule.forRoot(routes)],
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
