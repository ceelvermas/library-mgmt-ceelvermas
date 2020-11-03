package com.lms.service;

import com.lms.domain.BookLedger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created By CL Verma on 10/4/20
 */
public interface BookLedgerService {
    /**
     * Save or Update BookLedger Entity
     * @param bookLedger
     * @return
     */
    BookLedger save(BookLedger bookLedger);

    /**
     * Find BookLedger by id
     * @param id
     * @return
     */
    Optional<BookLedger> findOne(Long id);

    /**
     * Find all BookLedger
     * @param pageable
     * @return
     */
    Page<BookLedger> findAll(Pageable pageable);

    /**
     * Search BookLedgers
     * @param query
     * @param pageable
     * @return
     */
    Page<BookLedger> search(String query, Pageable pageable);

    /**
     * Delete BookLedger by id
     */
    void delete(Long id);
}
