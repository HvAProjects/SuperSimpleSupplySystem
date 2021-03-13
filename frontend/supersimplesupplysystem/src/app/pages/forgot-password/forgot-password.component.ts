import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {TokenStorageService} from '../../services/token-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  isLoggedIn;
  message;
  error = false;

  form: any = {};

  forgotPasswordForm = new FormGroup({
    emailAddress: new FormControl(new FormControl('', [
      Validators.required,
      Validators.email
    ]))
  });

  constructor(private authService: AuthService,
              private tokenStorage: TokenStorageService,
              private router: Router) {
    if (this.tokenStorage.getToken()) { // User is already loggedin
      this.router.navigate(['/home']);
    }
  }

  ngOnInit(): void {
  }

  onSubmit(): void {
    if (this.forgotPasswordForm.valid) {
      this.authService.forgotPassword(this.forgotPasswordForm.value.emailAddress).subscribe(
        data => {
          this.message = 'If the email address is found, an email is sent to reset the password';
          this.error = false;
        },
        err => {
          this.message = 'Something went horribly wrong!';
          this.error = true;
        }
      );
    }
  }
}
