import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { credential } from 'src/app/comman/credential';

@Component({
  selector: 'app-manage-role',
  templateUrl: './manage-role.component.html',
  styleUrls: ['./manage-role.component.css']
})
export class ManageRoleComponent {

  constructor(private storage: credential,private router : Router) {}

  BackPage() {
    if(this.storage.getRole()=='EDA') {
      this.router.navigate(['/home/Eda-page/Manager']);
    } else {
      this.router.navigate(['/home/Edo-page/officer-manager'])
    }
    
  }

  view(hell:string) {
    
    this.router.navigate(['/home/Eda-page/Manager/Manage-Role/Role-info']);
   
  }

  showForm() {
    this.router.navigate(['/home/Eda-page/Manager/Manage-Role/New-Role']);
  }
}
