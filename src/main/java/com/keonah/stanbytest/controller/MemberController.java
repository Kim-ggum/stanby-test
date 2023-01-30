package com.keonah.stanbytest.controller;

import com.keonah.stanbytest.dto.MemberCreateDto;
import com.keonah.stanbytest.dto.SignupDto;
import com.keonah.stanbytest.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
