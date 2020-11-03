import { Book } from '../book/book.model';
import { Member } from '../member/member.model';

export  class BookLadger {
    book?: Book;
    member?: Member;
    issuedDate?: string;
    receivedDate?: string;
    status: string;
}
