package com.keonah.stanbytest.service;

import com.keonah.stanbytest.dto.ExpenditureDTO;
import com.keonah.stanbytest.dto.ExpenditureListDTO;
import com.keonah.stanbytest.mapping.ExpenditureInfoMapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenditureService {

    void inputExpenditure(ExpenditureDTO expenditureDto);
    Page<ExpenditureInfoMapping> getExpenditureList(ExpenditureListDTO expenditureListDto, Pageable pageable);

}
