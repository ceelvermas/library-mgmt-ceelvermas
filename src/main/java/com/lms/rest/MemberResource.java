package com.lms.rest;

import com.lms.domain.Member;
import com.lms.rest.util.PaginationUtil;
import com.lms.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created By CL Verma on 10/4/20
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MemberResource {

    private final MemberService memberService;

    public MemberResource(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@Validated @RequestBody Member member) throws Exception {
        if (nonNull(member.getId()))
            throw new Exception("A new Member cannot already have an ID");
        Member result = memberService.save(member);
        return ResponseEntity.created(new URI("/api/members" + result.getId()))
                .body(result);
    }

    @PutMapping("/members")
    public ResponseEntity<Member> updateMember(@Validated @RequestBody Member member) throws Exception {
        if (isNull(member.getId()))
            throw new Exception("Invalid Member ID");
        Member result = memberService.save(member);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> findMember(@PathVariable Long id) {
        Member result = memberService.findOne(id).get();
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> findAllMember(Pageable pageable) {
        Page<Member> page = memberService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/members");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/_search/members")
    public ResponseEntity<List<Member>> searchMembers(@RequestParam String query, Pageable pageable) {
        Page<Member> page = memberService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/_search/members");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("members/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<String>("Member has been deleted successfully", HttpStatus.OK);
    }
}
