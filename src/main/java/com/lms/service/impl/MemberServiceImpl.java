package com.lms.service.impl;

import com.lms.domain.Member;
import com.lms.repository.MemberRepository;
import com.lms.service.MemberService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created By CL Verma on 10/4/20
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @Override
    public Page<Member> search(String query, Pageable pageable) {
        return memberRepository.findAll(searchText(query), pageable);
    }

    @Override
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    private Example<Member> searchText(String text) {
        Member member = new Member();
        member.setFirstName(text);
        member.setLastName(text);
        member.setEmail(text);
        member.setMobile(text);
        ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("mobile", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        return  Example.of(member, customExampleMatcher);
    }
}
