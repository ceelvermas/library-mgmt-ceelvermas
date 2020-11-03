package com.lms.service.impl;

import com.lms.domain.Book;
import com.lms.repository.BookRepository;
import com.lms.service.BookService;
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
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findOne(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> search(String query, Pageable pageable) {
        return bookRepository.findAll(searchText(query), pageable);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private Example<Book> searchText(String text) {
        Book book = new Book();
        book.setTitle(text);
        book.setSubject(text);
        book.setAuthor(text);
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("subject", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("author", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return  Example.of(book, customExampleMatcher);
    }
}
