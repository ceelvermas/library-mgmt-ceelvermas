import { Component, OnInit } from '@angular/core';
import { Observable, from } from 'rxjs';
import { Book } from './book.model';
import { BookService } from './book.service';
import { Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'lms-book',
  templateUrl: './book.component.html',
})

export class BookComponent implements OnInit {

  books: Book[];
  currentSearch: string;
  constructor(private bookService: BookService, private router: Router) {}

  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.bookService
      .findAll({
          query: this.currentSearch,
          page: 0,
          size: 20
      })
      .subscribe(
          (res: HttpResponse<Book[]>) => { this.books = res.body; },
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
    this.bookService
      .search({
          query: query,
          page: 0,
          size: 20
      })
      .subscribe(
          (res: HttpResponse<Book[]>) => { this.books = res.body; },
          (res: HttpErrorResponse) => {}
      );
  }

  update(id: number) {
      this.router.navigate(['book', id]);
  }
}
