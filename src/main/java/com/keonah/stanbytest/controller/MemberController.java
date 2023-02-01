package com.keonah.stanbytest.controller;

import com.keonah.stanbytest.dto.MemberCreateDTO;
import com.keonah.stanbytest.dto.MemberUpdateDTO;
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

    // 회원 추가 (POST /members)
    @PostMapping("")
    public ResponseEntity createMember(@Valid @RequestBody MemberCreateDTO memberCreateDto) {
        if(!memberService.checkNameAndTeamDuplication(memberCreateDto.getName(), memberCreateDto.getTeam())) {
            if(!memberRepository.existsById(memberCreateDto.getId())) {
                memberService.createMember(memberCreateDto);
                return new ResponseEntity(memberCreateDto, HttpStatus.OK);
            } else {
                return new ResponseEntity("사원 번호는 중복될 수 없습니다.", HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity("한 부서에 같은 이름의 회원을 저장할 수 없습니다.", HttpStatus.CONFLICT);
        }

    }

    // 회원 정보 수정 (PUT /members/{no})
    @PutMapping("/{id}")
    public ResponseEntity updateMember(@PathVariable String id, @Valid @RequestBody MemberUpdateDTO memberUpdateDto) {
        if(!memberService.checkNameAndTeamDuplication(memberUpdateDto.getName(), memberUpdateDto.getTeam())) {
            memberService.updateMember(id, memberUpdateDto);
            return new ResponseEntity(memberUpdateDto,HttpStatus.OK);
        } else {
            return new ResponseEntity("한 부서에 같은 이름의 회원을 저장할 수 없습니다.", HttpStatus.CONFLICT);
        }

    }

    // 회원 삭제 (DELETE /members/{no})
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable String id) {
        if(memberRepository.findById(id) != null) {
            memberService.deleteMember(id);
            return new ResponseEntity("삭제 완료.", HttpStatus.OK);
        } else {
            return new ResponseEntity("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }

    }

    // 회원 목록 조회 (GET /members)
    @GetMapping("")
    public ResponseEntity getMemberList() {
        return new ResponseEntity (memberService.memberList(), HttpStatus.OK);
    }
}
