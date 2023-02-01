package com.keonah.stanbytest.controller;

import com.keonah.stanbytest.dto.MoneyInputDTO;
import com.keonah.stanbytest.dto.MoneyListDTO;
import com.keonah.stanbytest.entity.MemberEntity;
import com.keonah.stanbytest.repository.MemberRepository;
import com.keonah.stanbytest.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    // 회비 입금 (POST /money)
    @PostMapping("")
    public ResponseEntity inputMoney(@Valid @RequestBody MoneyInputDTO moneyInputDto) {

        MemberEntity memberEntity = memberRepository.findById(moneyInputDto.getMember());

        if(memberEntity == null) {
            return new ResponseEntity("존재하지 않는 회원입니다.", HttpStatus.BAD_REQUEST);
        }

        moneyService.inputMoney(moneyInputDto);
        return new ResponseEntity(moneyInputDto,HttpStatus.CREATED);

    }

    // 입금 내역 조회 (GET /money)
    @GetMapping("")
    public ResponseEntity getMoneyList(@Valid @RequestBody MoneyListDTO moneyListDto, Pageable pageable) {
        return new ResponseEntity(moneyService.getMoneyList(moneyListDto, pageable), HttpStatus.OK);
    }

    // 잔액 조회 (GET /money/total)
    @GetMapping("/total")
    public ResponseEntity getMoney() {
        return new ResponseEntity(moneyService.getMoney(), HttpStatus.OK);
    }


}
