import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Movie } from '../_model/movie.model';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  movieChange = new Subject<Movie[]>();
  changeMessage = new Subject<string>();

  url: string = `${environment.HOST}/api/v1/movies`;

  constructor(private http: HttpClient) { }

  listAll(p: number, s: number) {
    return this.http.get<any>(`${this.url}?pageNumber=${p}&pageSize=${s}`);
  }

  listById(idMovie: number) {
    return this.http.get<Movie>(`${this.url}/${idMovie}`);
  }

  register(articulo: Movie) {
    return this.http.post(this.url, articulo);
  }

  modify(idMovie: number, articulo: Movie) {
    return this.http.put(`${this.url}/${idMovie}`, articulo);
  }

  remove(idMovie: number) {
    return this.http.delete(`${this.url}/${idMovie}`);
  }

}
