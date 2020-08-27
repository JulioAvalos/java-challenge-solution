import { Component } from '@angular/core';
import { LoginService } from './_service/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  // menus: Menu[];

  constructor(
    // private menuService: MenuService,
    public loginService: LoginService
    ) {

  }

  ngOnInit() {
    // this.menuService.menuCambio.subscribe(data => {
    //   this.menus = data;
    // });
  }

}
