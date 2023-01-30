package com.keonah.stanbytest.controller;

import com.keonah.stanbytest.dto.ExpenditureDto;
import com.keonah.stanbytest.dto.ExpenditureListDto;
import com.keonah.stanbytest.service.ExpenditureService;
import com.keonah.stanbytest.service.MoneyService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("")
    public ResponseEntity inputExpenditure(@Valid @RequestBody ExpenditureDto expenditureDto) {
        if(moneyService.getMoney() < expenditureDto.getAmount()) {
            return new ResponseEntity("Exceed the money you have.",HttpStatus.BAD_REQUEST);
        }

        expenditureService.inputExpenditure(expenditureDto);

        return new ResponseEntity(expenditureDto, HttpStatus.CREATED);

    }

    @GetMapping("")
    public ResponseEntity getExpenditureList(@Valid @RequestBody ExpenditureListDto expenditureListDto) {

        return new ResponseEntity(expenditureService.getExpenditureList(expenditureListDto), HttpStatus.OK);

    }
}
