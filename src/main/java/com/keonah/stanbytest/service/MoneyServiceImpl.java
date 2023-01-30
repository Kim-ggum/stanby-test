package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MoneyInputDto;
import com.keonah.stanbytest.dto.MoneyListDto;
import com.keonah.stanbytest.entity.MemberEntity;
import com.keonah.stanbytest.entity.MoneyEntity;
import com.keonah.stanbytest.repository.ExpenditureRepository;
import com.keonah.stanbytest.repository.MemberRepository;
import com.keonah.stanbytest.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoneyServiceImpl implements MoneyService{

    private final MoneyRepository moneyRepository;
    private final MemberRepository memberRepository;
    private final ExpenditureRepository expenditureRepository;

    @Override
    public void inputMoney(MoneyInputDto moneyInputDto) {
        MemberEntity memberEntity = memberRepository.findByNo(moneyInputDto.getMember());

        MoneyEntity moneyEntity = MoneyEntity.builder()
                .member(memberEntity.getNo())
                .amount(moneyInputDto.getAmount())
                .build();

        moneyRepository.save(moneyEntity);
    }

    @Override
    public List<MoneyEntity> getMoneyList(MoneyListDto moneyListDto) {

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
            return moneyRepository.findAllByCreatedDateBetweenAndMember(startDate, endDate, moneyListDto.getMember());
        } else {
            return moneyRepository.findAllByCreatedDateBetween(startDate, endDate);
        }

    }

    @Override
    public Long getMoney() {

        Long money =  Long.valueOf(moneyRepository.findAll().stream().map(m -> m.getAmount()).mapToInt(Long::intValue).sum()) -
                Long.valueOf(expenditureRepository.findAll().stream().map(m -> m.getAmount()).mapToInt(Long::intValue).sum());

        return money;
    }
}
