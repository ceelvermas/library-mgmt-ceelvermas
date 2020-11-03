import { Component, OnInit } from '@angular/core';
import { Member } from './member.model';
import { MemberService } from './member.service';
import { Observable, from } from 'rxjs';
import { Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'lms-member',
  templateUrl: './member.component.html',
})
export class MemberComponent implements OnInit {

  members: Member[];
  currentSearch: string;

  constructor(private memberService: MemberService, private router: Router) {}

  ngOnInit() {
    this.loadAll();
  }

  loadAll() {
    this.memberService
      .findAll({
          query: '*',
          page: 0,
          size: 20
      })
      .subscribe(
          (res: HttpResponse<Member[]>) => { this.members = res.body; },
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
    this.memberService
      .search({
          query: query,
          page: 0,
          size: 20
      })
      .subscribe(
          (res: HttpResponse<Member[]>) => { this.members = res.body; },
          (res: HttpErrorResponse) => {}
      );
  }

  update(id: number) {
      this.router.navigate(['member', id]);
  }

}
