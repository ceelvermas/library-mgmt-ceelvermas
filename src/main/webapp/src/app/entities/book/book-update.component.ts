import { Component, OnInit } from '@angular/core';
import { Book } from './book.model';
import { BookService } from './book.service';
import { Router, ActivatedRoute } from '@angular/router';




@Component({
  selector: 'lms-book',
  templateUrl: './book-update.component.html',
})



export class BookUpdateComponent implements OnInit {

    id: number;
    book: Book = new Book();
    category = ['ACTION_AND_ADVENTURES', 'CLASSICS', 'COMIC_BOOK', 'GRAPHIC_NOVEL', 'SCIENCE', 'TECHNOLOGY', 'MATHAMATICS'];
    submitted = false;

  constructor(private bookService: BookService,
    private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    if (this.id != null) {
    this.bookService.find(this.id)
    .subscribe(data => {
      this.book = data;
    },
    error => console.log(error));
  }
  }

  save() {
    this.submitted = true;
    this.bookService.create(this.book).subscribe(data => {
      this.list();
    },
    error => console.log(error));
  }

  list() {
    this.router.navigate(['/']);
  }

  cancel() {
    this.router.navigate(['/']);
  }
}
