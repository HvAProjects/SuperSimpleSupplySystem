import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BoardAdminComponent} from './pages/board-admin/board-admin.component';
import {ProfileComponent} from './pages/profile/profile.component';
import {HomeComponent} from './pages/home/home.component';
import {BoardUserComponent} from './pages/board-user/board-user.component';
import {BoardModeratorComponent} from './pages/board-moderator/board-moderator.component';
import {HouseholdComponent} from './pages/household/household.component';
import {ScannerComponent} from './pages/scanner/scanner.component';
import {ProductComponent} from './pages/product/product.component';
import {HouseholdSettingsComponent} from './pages/household-settings/household-settings.component';
import {DashboardComponent} from './pages/dashboard/dashboard.component';
import { AuthGuard } from '@auth0/auth0-angular';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuard]},
  {path: 'user', component: BoardUserComponent, canActivate: [AuthGuard]},
  {path: 'mod', component: BoardModeratorComponent, canActivate: [AuthGuard]},
  {path: 'admin', component: BoardAdminComponent, canActivate: [AuthGuard]},
  {path: 'household', component: HouseholdComponent, canActivate: [AuthGuard]},
  {path: 'household/:householdId', component: ProductComponent, canActivate: [AuthGuard]},
  {path: 'scanner', component: ScannerComponent, canActivate: [AuthGuard]},
  {path: 'household-settings/:householdId', component: HouseholdSettingsComponent, canActivate: [AuthGuard]},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: '', redirectTo: 'home', pathMatch: 'full'}

];

@NgModule({
  // imports: [RouterModule.forRoot(routes)],
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
