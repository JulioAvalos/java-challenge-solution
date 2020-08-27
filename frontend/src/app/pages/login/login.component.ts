import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/_service/login.service';
import { environment } from 'src/environments/environment';
import { User } from 'src/app/_model/user.model';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginFormGroup: FormGroup;

  usuario: string;
  clave: string;
  mensaje: string;
  error: string;
  hide = true;

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginFormGroup = this.formBuilder.group({
      'username': new FormControl(''),
      'password': new FormControl('')
    });

  }

  login() {
    let usuario = new User();

    usuario.username = this.loginFormGroup.value['username'];
    usuario.password = this.loginFormGroup.value['password'];

    this.loginService.login(usuario.username, usuario.password).subscribe(data => {
      console.log(data);
      const helper = new JwtHelperService();
      localStorage.setItem(environment.TOKEN_NAME, data.access_token);

      let decodedToken = helper.decodeToken(data.access_token);
      console.log(decodedToken);

      this.router.navigate(['/movies']);
    },
    err => {
      this.error = 'Error: Incorrect Username or Password!';
    });
  }

}
