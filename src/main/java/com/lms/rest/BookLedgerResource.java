package com.lms.rest;

import com.lms.domain.BookLedger;
import com.lms.rest.util.PaginationUtil;
import com.lms.service.BookLedgerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created By CL Verma on 10/4/20
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class BookLedgerResource {

    private final BookLedgerService bookLedgerService;

    public BookLedgerResource(BookLedgerService bookLedgerService) {
        this.bookLedgerService = bookLedgerService;
    }

    @PostMapping("/book-ledgers")
    public ResponseEntity<BookLedger> createBookLedger(@Validated @RequestBody BookLedger bookLedger) throws Exception {
        if (nonNull(bookLedger.getId()))
            throw new Exception("A new BookLedger cannot already have an ID");
        bookLedger.setIssuedDate(LocalDateTime.now());
        bookLedger.setStatus("ISSUED");
        BookLedger result = bookLedgerService.save(bookLedger);
        return ResponseEntity.created(new URI("/api/book-ledgers" + result.getId()))
                .body(result);
    }

    @PutMapping("/book-ledgers")
    public ResponseEntity<BookLedger> updateBookLedger(@Validated @RequestBody BookLedger bookLedger) throws Exception {
        if (isNull(bookLedger.getId()))
            throw new Exception("Invalid BookLedger ID");
        bookLedger.setReceivedDate(LocalDateTime.now());
        bookLedger.setStatus("RECEIVED");
        BookLedger result = bookLedgerService.save(bookLedger);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/book-ledgers/{id}")
    public ResponseEntity<BookLedger> findBookLedger(@PathVariable Long id) {
        BookLedger result = bookLedgerService.findOne(id).get();
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/book-ledgers")
    public ResponseEntity<List<BookLedger>> findAllBookLedgers(Pageable pageable) {
        Page<BookLedger> page = bookLedgerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/book-ledgers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/_search/book-ledgers")
    public ResponseEntity<List<BookLedger>> searchBookLedgers(@RequestParam String query, Pageable pageable) {
        Page<BookLedger> page = bookLedgerService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/_search/book-ledgers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("book-ledgers/{id}")
    public ResponseEntity<?> deleteBookLedger(@PathVariable Long id) {
        bookLedgerService.delete(id);
        return new ResponseEntity<String>("BookLedger has been deleted successfully", HttpStatus.OK);
    }
}
