import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent implements OnInit {

  message;
  error = false;

  constructor(private authService: AuthService,
              private route: ActivatedRoute,
              private router: Router) {
    const token = route.snapshot.queryParamMap.get('token');
    if (!token) {
      this.router.navigate(['/home']);
    }

    authService.activateAccount(token).subscribe(
      data => {
        this.message = data.message;
        this.error = false;
      },
      err => {
        this.message = err.error.message;
        this.error = true;
      });
  }

  ngOnInit(): void {
  }

}
