import { Component, OnInit } from '@angular/core';
import { BookLadger } from './bookLadger.model';
import { BookLadgerService } from './bookLadger.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Member } from '../member/member.model';
import { Book } from '../book/book.model';
import { BookService } from '../book/book.service';
import { MemberService } from '../member/member.service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';



@Component({
  selector: 'lms-book-ladger',
  templateUrl: './bookLadger-update.component.html',
})



export class BookLadgerUpdateComponent implements OnInit {

      id: number;
      members: Member[];
      books: Book[];
      bookLadger: BookLadger = new BookLadger();
      submitted = false;

      constructor(
        private bookLadgerService: BookLadgerService,
        private memberService: MemberService,
        private bookService: BookService,
        private router: Router, private route: ActivatedRoute
      ) { }

      ngOnInit() {
        this.id = this.route.snapshot.params['id'];
        if (this.id != null) {
          this.bookLadgerService.find(this.id)
          .subscribe(data => {
            this.bookLadger = data;
          },
          error => console.log(error));
        }

        this.bookService
          .findAll({
              page: 0,
              size: 20
          })
          .subscribe(
              (res: HttpResponse<Book[]>) => { this.books = res.body; },
              (res: HttpErrorResponse) => {}
          );

          this.memberService
            .findAll({
                page: 0,
                size: 20
            })
            .subscribe(
                (res: HttpResponse<Member[]>) => { this.members = res.body; },
                (res: HttpErrorResponse) => {}
            );
      }

      save() {
        this.submitted = true;
        this.bookLadgerService.create(this.bookLadger).subscribe(data => {
          this.list();
        },
        error => console.log(error));
      }

      list() {
        this.router.navigate(['/book-ladger']);
      }

      cancel() {
        this.list();
      }

}
