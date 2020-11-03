import { Component, OnInit } from '@angular/core';
import { Member } from './member.model';
import { MemberService } from './member.service';
import { Router, ActivatedRoute } from '@angular/router';



@Component({
  selector: 'lms-member',
  templateUrl: './member-update.component.html',
})



export class MemberUpdateComponent implements OnInit {

    id: number;
    member: Member = new Member();
    gender = ['Male', 'Female'];
    members = ['STUDENT', 'FACULTY'];
    submitted = false;

  constructor(private memberService: MemberService,
    private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    if (this.id != null) {
    this.memberService.find(this.id)
    .subscribe(data => {
      this.member = data;
    },
    error => console.log(error));
  }
  }

  save() {
    this.submitted = true;
    this.memberService.create(this.member).subscribe(data => {
      this.list();
    },
    error => console.log(error));
  }

  list() {
    this.router.navigate(['/member']);
  }

  cancel() {
    this.router.navigate(['/member']);
  }
}
