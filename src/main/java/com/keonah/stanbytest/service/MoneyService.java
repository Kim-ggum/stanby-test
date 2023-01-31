package com.keonah.stanbytest.service;

import com.keonah.stanbytest.mapping.MoneyInfoMapping;
import com.keonah.stanbytest.dto.MoneyInputDto;
import com.keonah.stanbytest.dto.MoneyListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MoneyService {
    void inputMoney(MoneyInputDto moneyInputDto);

    Page<MoneyInfoMapping> getMoneyList(MoneyListDto moneyListDto, Pageable pageable);

    Long getMoney();
}
