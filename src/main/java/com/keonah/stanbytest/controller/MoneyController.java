package com.keonah.stanbytest.controller;

import com.keonah.stanbytest.dto.MemberCreateDto;
import com.keonah.stanbytest.dto.MoneyInputDto;
import com.keonah.stanbytest.dto.MoneyListDto;
import com.keonah.stanbytest.entity.MemberEntity;
import com.keonah.stanbytest.repository.MemberRepository;
import com.keonah.stanbytest.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/money")
public class MoneyController {

    private final MoneyService moneyService;
    private final MemberRepository memberRepository;

    @PostMapping("")
    public ResponseEntity inputMoney(@Valid @RequestBody MoneyInputDto moneyInputDto) {

        MemberEntity memberEntity = memberRepository.findByNo(moneyInputDto.getMember());

        if(memberEntity == null) {
            return new ResponseEntity("can not found member", HttpStatus.BAD_REQUEST);
        }

        moneyService.inputMoney(moneyInputDto);
        return new ResponseEntity(moneyInputDto,HttpStatus.CREATED);

    }

    @GetMapping("")
    public ResponseEntity getMoneyList(@Valid @RequestBody MoneyListDto moneyListDto, Pageable pageable) {
        return new ResponseEntity(moneyService.getMoneyList(moneyListDto, pageable), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getMoney() {
        return new ResponseEntity(moneyService.getMoney(), HttpStatus.OK);
    }


}
