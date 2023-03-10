package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.ExpenditureDTO;
import com.keonah.stanbytest.dto.ExpenditureListDTO;
import com.keonah.stanbytest.entity.ExpenditureEntity;
import com.keonah.stanbytest.mapping.ExpenditureInfoMapping;
import com.keonah.stanbytest.repository.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ExpenditureServiceImpl implements ExpenditureService {

    private final ExpenditureRepository expenditureRepository;

    @Override
    public void inputExpenditure(ExpenditureDTO expenditureDto) {
        ExpenditureEntity expenditureEntity = ExpenditureEntity.builder()
                .purpose(expenditureDto.getPurpose())
                .amount(expenditureDto.getAmount())
                .date(LocalDate.parse(expenditureDto.getDate(), DateTimeFormatter.ofPattern("yyyy.MM.dd")))
                .build();

        expenditureRepository.save(expenditureEntity);
    }

    @Override
    public Page<ExpenditureInfoMapping> getExpenditureList(ExpenditureListDTO expenditureListDto, Pageable pageable) {
        LocalDate startDate;
        LocalDate endDate;

        if(expenditureListDto.getStartDate() == null) {
            startDate = expenditureRepository.findTopByOrderByDate().getDate();
        } else {
            startDate = LocalDate.parse(expenditureListDto.getStartDate(), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }

        if(expenditureListDto.getEndDate() == null) {
            endDate = LocalDate.now();
        } else {
            endDate = LocalDate.parse(expenditureListDto.getEndDate(), DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }

        return expenditureRepository.findAllByDateBetweenOrderByDate(startDate, endDate, pageable);

    }


}
