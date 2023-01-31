package com.keonah.stanbytest.service;

import com.keonah.stanbytest.mapping.MoneyInfoMapping;
import com.keonah.stanbytest.dto.MoneyInputDTO;
import com.keonah.stanbytest.dto.MoneyListDTO;
import com.keonah.stanbytest.entity.MemberEntity;
import com.keonah.stanbytest.entity.MoneyEntity;
import com.keonah.stanbytest.repository.ExpenditureRepository;
import com.keonah.stanbytest.repository.MemberRepository;
import com.keonah.stanbytest.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MoneyServiceImpl implements MoneyService{

    private final MoneyRepository moneyRepository;
    private final MemberRepository memberRepository;
    private final ExpenditureRepository expenditureRepository;

    @Override
    public void inputMoney(MoneyInputDTO moneyInputDto) {
        MemberEntity memberEntity = memberRepository.findByNo(moneyInputDto.getMember());

        MoneyEntity moneyEntity = MoneyEntity.builder()
                .member(memberEntity.getNo())
                .amount(moneyInputDto.getAmount())
                .build();

        moneyRepository.save(moneyEntity);
    }

    @Override
    public Page<MoneyInfoMapping> getMoneyList(MoneyListDTO moneyListDto, Pageable pageable) {

        LocalDate startDate;
        LocalDate endDate;

        if(moneyListDto.getStartDate() == null) {
            startDate = moneyRepository.findTopByOrderByCreatedDate().getCreatedDate();
        } else {
            startDate = LocalDate.parse(moneyListDto.getStartDate(), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }

        if(moneyListDto.getEndDate() == null) {
            endDate = LocalDate.now();
        } else {
            endDate = LocalDate.parse(moneyListDto.getEndDate(), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }

        if(moneyListDto.getMember() != null) {
            return moneyRepository.findAllByCreatedDateBetweenAndMember(startDate, endDate, moneyListDto.getMember(), pageable);
        } else {
            return moneyRepository.findAllByCreatedDateBetween(startDate, endDate, pageable);
        }

    }

    @Override
    public Long getMoney() {

        Long money =  Long.valueOf(moneyRepository.findAll().stream().map(m -> m.getAmount()).mapToInt(Long::intValue).sum()) -
                Long.valueOf(expenditureRepository.findAll().stream().map(m -> m.getAmount()).mapToInt(Long::intValue).sum());

        return money;
    }
}
