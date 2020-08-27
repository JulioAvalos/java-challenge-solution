import { Component, OnInit, ViewChild } from '@angular/core';
import { Movie } from '../../_model/movie.model';
import { MovieService } from '../../_service/movie.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.scss']
})
export class MovieComponent implements OnInit {

  quantity: number = 0;
  dataSource: MatTableDataSource<Movie>;
  displayedColumns: string[] = ['image_url', 'title', 'description', 'rental_price', 'sale_price', 'likes']; //, 'options'
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(
    private movieService: MovieService,
    private dialog: MatDialog,
    private snack: MatSnackBar,
    public route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.movieService.movieChange.subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      console.log(data);
      this.dataSource.sort = this.sort;
    });

    this.movieService.changeMessage.subscribe(data => {
      this.snack.open(data, 'Notice', {
        duration: 2000
      });
    });

    this.movieService.listAll(0, 5).subscribe(data => {
      this.quantity = data.totalElements;
      this.dataSource = new MatTableDataSource(data.content);
      this.dataSource.sort = this.sort;
      console.log(data);
    });
  }

  filter(value: string) {
    this.dataSource.filter = value.trim().toLowerCase();
  }

  showMore(e: any) {
    this.movieService.listAll(e.pageIndex, e.pageSize).subscribe(data => {
      this.quantity = data.totalElements;
      this.dataSource = new MatTableDataSource(data.content);
    });
  }

  displayMovie(movie?: Movie) {
    const art = movie != null ? movie : new Movie();
  }

}
