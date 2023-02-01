package com.keonah.stanbytest.controller;

import com.keonah.stanbytest.dto.ExpenditureDTO;
import com.keonah.stanbytest.dto.ExpenditureListDTO;
import com.keonah.stanbytest.service.ExpenditureService;
import com.keonah.stanbytest.service.MoneyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expenditure")
public class ExpenditureController {

    private final MoneyService moneyService;
    private final ExpenditureService expenditureService;

    // 회비 사용내역 저장 (POST /expenditure)
    @PostMapping("")
    public ResponseEntity inputExpenditure(@Valid @RequestBody ExpenditureDTO expenditureDto) {
        if(moneyService.getMoney() < expenditureDto.getAmount()) {
            return new ResponseEntity("잔액보다 큰 금액입니다.",HttpStatus.BAD_REQUEST);
        }

        expenditureService.inputExpenditure(expenditureDto);

        return new ResponseEntity(expenditureDto, HttpStatus.CREATED);

    }

    // 회비 사용내역 조회 (GET /expenditure)
    @GetMapping("")
    public ResponseEntity getExpenditureList(@Valid @RequestBody ExpenditureListDTO expenditureListDto, Pageable pageable) {

        return new ResponseEntity(expenditureService.getExpenditureList(expenditureListDto, pageable), HttpStatus.OK);

    }
}
