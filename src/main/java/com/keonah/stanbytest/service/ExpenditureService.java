package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.ExpenditureDto;
import com.keonah.stanbytest.dto.ExpenditureListDto;
import com.keonah.stanbytest.entity.ExpenditureEntity;
import com.keonah.stanbytest.mapping.ExpenditureInfoMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ExpenditureService {

    void inputExpenditure(ExpenditureDto expenditureDto);
    Page<ExpenditureInfoMapping> getExpenditureList(ExpenditureListDto expenditureListDto, Pageable pageable);

}
