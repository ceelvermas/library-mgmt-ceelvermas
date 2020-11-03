package com.lms.service;

import com.lms.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created By CL Verma on 10/4/20
 */
public interface BookService {
    /**
     * Save or Update Book Entity
     * @param book
     * @return
     */
    Book save(Book book);

    /**
     * Find Book by id
     * @param id
     * @return
     */
    Optional<Book> findOne(Long id);

    /**
     * Find all Book
     * @param pageable
     * @return
     */
    Page<Book> findAll(Pageable pageable);

    /**
     * Search Books
     * @param query
     * @param pageable
     * @return
     */
    Page<Book> search(String query, Pageable pageable);

    /**
     * Delete Book by id
     */
    void delete(Long id);
}
