package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.ExpenditureDto;
import com.keonah.stanbytest.entity.ExpenditureEntity;
import com.keonah.stanbytest.repository.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ExpenditureServiceImpl implements ExpenditureService {

    private final ExpenditureRepository expenditureRepository;

    @Override
    public void inputExpenditure(ExpenditureDto expenditureDto) {
        ExpenditureEntity expenditureEntity = ExpenditureEntity.builder()
                .purpose(expenditureDto.getPurpose())
                .amount(expenditureDto.getAmount())
                .date(LocalDate.parse(expenditureDto.getDate(), DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();

        expenditureRepository.save(expenditureEntity);
    }
}
