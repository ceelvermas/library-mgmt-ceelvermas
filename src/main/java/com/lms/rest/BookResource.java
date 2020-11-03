package com.lms.rest;

import com.lms.domain.Book;
import com.lms.rest.util.PaginationUtil;
import com.lms.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created By CL Verma on 10/4/20
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BookResource {

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@Validated @RequestBody Book book) throws Exception {
        if (nonNull(book.getId()))
            throw new Exception("A new Book cannot already have an ID");
        book.setPublicationDate(LocalDate.now());
        Book result = bookService.save(book);
        return ResponseEntity.created(new URI("/api/books" + result.getId()))
                .body(result);
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@Validated @RequestBody Book book) throws Exception {
        if (isNull(book.getId()))
            throw new Exception("Invalid Book ID");
        Book result = bookService.save(book);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> findBook(@PathVariable Long id) {
        Book result = bookService.findOne(id).get();
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllBooks(Pageable pageable) {
        Page<Book> page = bookService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/books");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/_search/books")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query, Pageable pageable) {
        Page<Book> page = bookService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/_search/books");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return new ResponseEntity<String>("Book has been deleted successfully", HttpStatus.OK);
    }
}
