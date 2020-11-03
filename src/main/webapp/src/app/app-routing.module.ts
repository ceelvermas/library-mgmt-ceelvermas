import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookComponent } from './entities/book/book.component';
import { BookUpdateComponent } from './entities/book/book-update.component';
import { MemberComponent } from './entities/member/member.component';
import { MemberUpdateComponent } from './entities/member/member-update.component';
import { BookLadgerComponent } from './entities/bookLadger/bookLadger.component';
import { BookLadgerUpdateComponent } from './entities/bookLadger/bookLadger-update.component';


const routes: Routes = [
    { path: '', component: BookComponent },
    { path: 'book/new', component: BookUpdateComponent },
    { path: 'book/{id}', component: BookUpdateComponent },
    { path: 'member', component: MemberComponent },
    { path: 'member/new', component: MemberUpdateComponent },
    { path: 'member/{id}', component: MemberUpdateComponent },
    { path: 'book-ladger', component: BookLadgerComponent },
    { path: 'book-ladger/new', component: BookLadgerUpdateComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
