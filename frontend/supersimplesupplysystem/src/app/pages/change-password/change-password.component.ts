import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {TokenStorageService} from '../../services/token-storage.service';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  message;
  error = false;
  token;

  changePasswordForm = new FormGroup({
    oldPassword: new FormControl('', [
      Validators.required
    ]),
    newPassword: new FormControl('', [
      Validators.required
    ]),
    newPasswordConfirm: new FormControl('', [
      Validators.required,
      this.matchValues('newPassword')
    ])
  });

  constructor(private userService: UserService,
              private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private router: Router,
              private route: ActivatedRoute) {

    this.token = route.snapshot.queryParamMap.get('token');
    if (!this.tokenStorage.getToken() && !this.token) { // User is not logged in and has no token
      this.router.navigate(['/home']);
    }
    this.changePasswordForm.controls.newPassword.valueChanges.subscribe(() => {
      this.changePasswordForm.controls.newPasswordConfirm.updateValueAndValidity();
    });
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.changePasswordForm.valid) {
      let subscription;
      if (this.token) {
        subscription = this.authService.changePasswordWithToken(
          this.changePasswordForm.value.oldPassword,
          this.changePasswordForm.value.newPassword,
          this.changePasswordForm.value.newPasswordConfirm,
          this.token,
          );
      } else {
        subscription = this.userService.changePassword(
          this.changePasswordForm.value.oldPassword,
          this.changePasswordForm.value.newPassword,
          this.changePasswordForm.value.newPasswordConfirm
        );
      }
      subscription.subscribe(
        data => {
          this.message = 'If the email address is found, an email is sent to reset the password';
          this.error = false;
        },
        err => {
          this.message = 'Something went horribly wrong!';
          this.error = true;
        });
    }
  }

  matchValues(
    matchTo: string // name of the control to match to
  ): (AbstractControl) => ValidationErrors | null {
    console.log('matching');
    return (control: AbstractControl): ValidationErrors | null => {
      return !!control.parent &&
      !!control.parent.value &&
      control.value === control.parent.controls[matchTo].value
        ? null
        : { isMatching: false };
    };
  }
}
