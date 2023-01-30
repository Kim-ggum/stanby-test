package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.MoneyInputDto;
import com.keonah.stanbytest.entity.MemberEntity;
import com.keonah.stanbytest.entity.MoneyEntity;
import com.keonah.stanbytest.repository.MemberRepository;
import com.keonah.stanbytest.repository.MoneyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
@RequiredArgsConstructor
public class MoneyServiceImpl implements MoneyService{

    private final MoneyRepository moneyRepository;
    private final MemberRepository memberRepository;

    @Override
    public void inputMoney(MoneyInputDto moneyInputDto) {
        MemberEntity memberEntity = memberRepository.findByNo(moneyInputDto.getMember());

        MoneyEntity moneyEntity = MoneyEntity.builder()
                .member(memberEntity.getNo())
                .amount(moneyInputDto.getAmount())
                .build();

        moneyRepository.save(moneyEntity);
    }
}
