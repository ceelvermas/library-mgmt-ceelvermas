import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BookComponent } from './entities/book/book.component';
import { BookUpdateComponent } from './entities/book/book-update.component';
import { MemberComponent } from './entities/member/member.component';
import { BookLadgerComponent } from './entities/bookLadger/bookLadger.component';
import { MemberUpdateComponent } from './entities/member/member-update.component';
import { BookLadgerUpdateComponent } from './entities/bookLadger/bookLadger-update.component';


@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    BookUpdateComponent,
    MemberComponent,
    MemberUpdateComponent,
    BookLadgerComponent,
    BookLadgerUpdateComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
