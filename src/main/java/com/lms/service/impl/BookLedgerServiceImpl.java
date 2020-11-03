package com.lms.service.impl;

import com.lms.domain.Book;
import com.lms.domain.BookLedger;
import com.lms.domain.Member;
import com.lms.repository.BookLedgerRepository;
import com.lms.service.BookLedgerService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created By CL Verma on 10/4/20
 */
@Service
@Transactional
public class BookLedgerServiceImpl implements BookLedgerService {

    private final BookLedgerRepository bookLedgerRepository;

    public BookLedgerServiceImpl(BookLedgerRepository bookLedgerRepository) {
        this.bookLedgerRepository = bookLedgerRepository;
    }

    @Override
    public BookLedger save(BookLedger bookLedger) {
        return bookLedgerRepository.save(bookLedger);
    }

    @Override
    public Optional<BookLedger> findOne(Long id) {
        return bookLedgerRepository.findById(id);
    }

    @Override
    public Page<BookLedger> findAll(Pageable pageable) {
        return bookLedgerRepository.findAll(pageable);
    }

    @Override
    public Page<BookLedger> search(String query, Pageable pageable) {
        return bookLedgerRepository.findAll(searchText(query), pageable);
    }

    @Override
    public void delete(Long id) {
        bookLedgerRepository.deleteById(id);
    }

    private Example<BookLedger> searchText(String text) {
        BookLedger bookLedger = new BookLedger();
        Member member = new Member();
        member.setFirstName(text);
        member.setLastName(text);
        Book book = new Book();
        book.setTitle(text);
        bookLedger.setBook(book);
        bookLedger.setMember(member);
        bookLedger.setStatus(text);
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("book.title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("member.firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("member.lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return  Example.of(bookLedger, customExampleMatcher);
    }
}
