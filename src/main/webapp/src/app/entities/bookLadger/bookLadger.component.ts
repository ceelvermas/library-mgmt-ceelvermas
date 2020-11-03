import { Component, OnInit } from '@angular/core';
import { BookLadger } from './bookLadger.model';
import { BookLadgerService } from './bookLadger.service';
import { Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';



@Component({
  selector: 'lms-bookLadger',
  templateUrl: './bookLadger.component.html',
})



export class BookLadgerComponent implements OnInit {

  bookLadgers: BookLadger[];
  currentSearch: string;

  constructor(private bookLadgerService: BookLadgerService, private router: Router) {}

  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.bookLadgerService
      .findAll({
          page: 0,
          size: 20
      })
      .subscribe(
          (res: HttpResponse<BookLadger[]>) => { this.bookLadgers = res.body; },
          (res: HttpErrorResponse) => {}
      );
  }

  onSearchTextChange(event) {
    if (this.currentSearch.length >= 3) {
      this.search(this.currentSearch);
    } else {
      this.loadAll();
    }
  }

  search(query) {
    this.bookLadgerService
      .search({
          query: query,
          page: 0,
          size: 20
      })
      .subscribe(
          (res: HttpResponse<BookLadger[]>) => { this.bookLadgers = res.body; },
          (res: HttpErrorResponse) => {}
      );
  }

  update(bookLadger, idx) {
      this.bookLadgerService.update(bookLadger)
      .subscribe(
        (res: HttpResponse<BookLadger>) => { this.loadAll(); },
        (res: HttpErrorResponse) => {}
      );
  }

}
