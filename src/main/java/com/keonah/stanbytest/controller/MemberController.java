package com.keonah.stanbytest.controller;

import com.keonah.stanbytest.dto.MemberCreateDto;
import com.keonah.stanbytest.dto.MemberUpdateDto;
import com.keonah.stanbytest.repository.MemberRepository;
import com.keonah.stanbytest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("")
    public ResponseEntity createMember(@Valid @RequestBody MemberCreateDto memberCreateDto) {
        if(!memberService.checkNameAndTeamDuplication(memberCreateDto.getName(), memberCreateDto.getTeam())) {
            if(!memberRepository.existsByNo(memberCreateDto.getNo())) {
                memberService.createMember(memberCreateDto);
                return new ResponseEntity(memberCreateDto, HttpStatus.OK);
            } else {
                return new ResponseEntity("사원 번호는 중복될 수 없습니다.", HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity("한 부서에 같은 이름의 회원을 저장할 수 없습니다.", HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/{no}")
    public ResponseEntity updateMember(@PathVariable String no, @Valid @RequestBody MemberUpdateDto memberUpdateDto) {
        if(!memberService.checkNameAndTeamDuplication(memberUpdateDto.getName(), memberUpdateDto.getTeam())) {
            memberService.updateMember(no, memberUpdateDto);
            return new ResponseEntity(memberUpdateDto,HttpStatus.OK);
        } else {
            return new ResponseEntity("한 부서에 같은 이름의 회원을 저장할 수 없습니다.", HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/{no}")
    public ResponseEntity deleteMember(@PathVariable String no) {
        if(memberRepository.findByNo(no) != null) {
            memberService.deleteMember(no);
            return new ResponseEntity("삭제 완료.", HttpStatus.OK);
        } else {
            return new ResponseEntity("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("")
    public ResponseEntity getMemberList() {
        return new ResponseEntity (memberService.memberList(), HttpStatus.OK);
    }
}
