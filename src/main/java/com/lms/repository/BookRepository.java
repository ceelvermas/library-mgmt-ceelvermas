package com.lms.repository;

import com.lms.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By CL Verma on 10/4/20
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
