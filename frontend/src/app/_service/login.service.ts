import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url: string = `${environment.HOST}/oauth/token`;

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  login(usuario: string, contrasena: string) {
    const body = `grant_type=password&username=${encodeURIComponent(usuario)}&password=${encodeURIComponent(contrasena)}`;

    return this.http.post<any>(this.url, body, {
      headers: new HttpHeaders()
        .set('Content-type', 'application/x-www-form-urlencoded; charset=UTF-8')
        .set('Authorization', 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD))
    });
  }

  isLogged() {
    let token = localStorage.getItem(environment.TOKEN_NAME);
    return token != null;

  }

  logOut() {
    let token = localStorage.getItem(environment.TOKEN_NAME);

    this.http.get(`${environment.HOST}/tokens/revoke/${token}`).subscribe(() => {
      localStorage.clear();
      this.router.navigate(['login']);
    });
  }

}
