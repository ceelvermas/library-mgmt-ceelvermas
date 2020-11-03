package com.lms.repository;

import com.lms.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By CL Verma on 10/4/20
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
}
