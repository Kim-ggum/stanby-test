package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.ExpenditureDto;
import com.keonah.stanbytest.dto.ExpenditureListDto;
import com.keonah.stanbytest.entity.ExpenditureEntity;

import java.time.LocalDate;
import java.util.List;

public interface ExpenditureService {

    void inputExpenditure(ExpenditureDto expenditureDto);
    List<ExpenditureEntity> getExpenditureList(ExpenditureListDto expenditureListDto);

}
