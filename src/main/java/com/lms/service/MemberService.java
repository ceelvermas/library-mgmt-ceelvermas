package com.lms.service;

import com.lms.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created By CL Verma on 10/4/20
 */
public interface MemberService {
    /**
     * Save or Update Member Entity
     * @param member
     * @return
     */
    Member save(Member member);

    /**
     * Find Member by id
     * @param id
     * @return
     */
    Optional<Member> findOne(Long id);

    /**
     * Find all Member
     * @return
     */
    Page<Member> findAll(Pageable pageable);

    /**
     * Search Members
     * @param query
     * @param pageable
     * @return
     */
    Page<Member> search(String query, Pageable pageable);

    /**
     * Delete Member by id
     */
    void delete(Long id);
}
