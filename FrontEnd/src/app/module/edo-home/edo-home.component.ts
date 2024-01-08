import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { credential } from 'src/app/comman/credential';
import { LoginServiceService } from 'src/app/service/login-service.service';

@Component({
  selector: 'app-edo-home',
  templateUrl: './edo-home.component.html',
  styleUrls: ['./edo-home.component.css']
})
export class EdoHomeComponent {

  constructor(
    private loginservice : LoginServiceService,
    private storage : credential,
    private router : Router
  ) {}


  logout() {
    this.router.navigate(['/home']);
    this.loginservice.logout();
    this.storage.removeTokenAndRole();
    this.storage.setLogin(false);
  } 

  

}
