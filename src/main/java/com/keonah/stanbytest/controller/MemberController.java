package com.keonah.stanbytest.controller;

import com.keonah.stanbytest.dto.MemberCreateDto;
import com.keonah.stanbytest.dto.MemberUpdateDto;
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

    @PostMapping("")
    public ResponseEntity createMember(@Valid @RequestBody MemberCreateDto memberCreateDto) {
        if(!memberService.checkNameAndTeamDuplication(memberCreateDto.getName(), memberCreateDto.getTeam())) {
            memberService.createMember(memberCreateDto);
            return new ResponseEntity(memberCreateDto,HttpStatus.OK);
        } else {
            return new ResponseEntity(memberCreateDto,HttpStatus.CONFLICT);
        }

    }

    @PutMapping("/{no}")
    public ResponseEntity updateMember(@PathVariable Long no, @Valid @RequestBody MemberUpdateDto memberUpdateDto) {
        if(!memberService.checkNameAndTeamDuplication(memberUpdateDto.getName(), memberUpdateDto.getTeam())) {
            memberService.updateMember(no, memberUpdateDto);
            return new ResponseEntity(memberUpdateDto,HttpStatus.OK);
        } else {
            return new ResponseEntity(memberUpdateDto,HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/{no}")
    public ResponseEntity deleteMember(@PathVariable Long no) {
            memberService.deleteMember(no);

            return new ResponseEntity(no,HttpStatus.OK);

    }

    @GetMapping("")
    public ResponseEntity getMemberList() {
        return new ResponseEntity (memberService.memberList(), HttpStatus.OK);
    }
}
